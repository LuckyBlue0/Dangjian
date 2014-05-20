

"""
    modify @ 2012-11-01
    
    modify conten:
    1.add dependency
    #add dependency
    for k,v in modules.items():
		if not project.add_dependency(v):
    failed.append(k)
    
    add more framwork
    for k,v in modules.items():
		if v.name == 'Three20UI':
    project.add_framework('QuartzCore.framework')
		if v.name == 'Three20Core':
    project.add_bundle()
    
		if not project.add_dependency(v):
    failed.append(k)
    
    
    
    2....
    
    
    """



import logging
import re
import os
import sys
from optparse import OptionParser
import Paths
from Pbxproj import Pbxproj



# Print the given project's dependencies to stdout.

def print_dependencies(name):
	pbxproj = Pbxproj.get_pbxproj_by_name(name)
	print str(pbxproj)+"..."
	if pbxproj.dependencies():
		[sys.stdout.write("\t"+x+"\n") for x in pbxproj.dependencies()]

def get_dependency_modules(dependency_names):
	dependency_modules = {}
	if not dependency_names:
		return dependency_modules

	for name in dependency_names:
		project = Pbxproj.get_pbxproj_by_name(name)
		dependency_modules[project.uniqueid()] = project

		dependencies = project.dependencies()
		if dependencies is None:
			print "Failed to get dependencies; it's possible that the given target doesn't exist."
			sys.exit(0)

		submodules = get_dependency_modules(dependencies)
		for guid, submodule in submodules.items():
			dependency_modules[guid] = submodule

	return dependency_modules

def add_modules_to_project(module_names, project, configs):
	logging.info(project)
	logging.info("Checking dependencies...")
	if project.dependencies() is None:
		logging.error("Failed to get dependencies. Check the error logs for more details.")
		sys.exit(0)
	if len(project.dependencies()) == 0:
		logging.info("\tNo dependencies.")
	else:
		logging.info("Existing dependencies:")
		[logging.info("\t"+x) for x in project.dependencies()]

	modules = get_dependency_modules(module_names)

	logging.info("Requested dependency list:")
	[logging.info("\t"+str(x)) for k,x in modules.items()]
	
	logging.info("Adding dependencies...")
	failed = []
#add dependency
	for k,v in modules.items():
		if v.name == 'DO':
			project.add_framework('MobileCoreServices.framework')
			project.add_framework('CFNetwork.framework')
			project.add_framework('SystemConfiguration.framework')
			project.add_framework('libz.dylib')
			project.add_framework('QuartzCore.framework')

		if not project.add_dependency(v):
			failed.append(k)

	if configs:
		for config in configs:
			project.add_header_search_path(config)

			project.add_build_setting(config, 'OTHER_LDFLAGS', '-ObjC')
	else:
		for configuration in project.configurations:
			project.add_header_search_path(configuration[1])

			for k,v in modules.items():
				project.add_build_setting(configuration[1], 'OTHER_LDFLAGS', '-ObjC')

	if len(failed) > 0:
		logging.error("Some dependencies failed to be added:")
		[logging.error("\t"+str(x)+"\n") for x in failed]


#main into
def main():
	parser = OptionParser()
	
	parser.add_option("-d", "--dependencies", dest="print_dependencies",
	                  help="Print dependencies for the given modules",
	                  action="store_true")
	
	parser.add_option("-v", "--verbose", dest="verbose",
	                  help="Display verbose output",
	                  action="store_true")

	parser.add_option("-p", "--project", dest="projects",
	                  help="Add the given modules to this project", action="append")

	parser.add_option("--xcode-version", dest="xcode_version",
	                  help="Set the xcode version you plan to open this project in. By default uses xcodebuild to determine your latest Xcode version.")
	
	parser.add_option("-c", "--config", dest="configs",
	                  help="Explicit configurations to add LLAdditions settings to (example: Debug). By default, ttmodule will add configuration settings to every configuration for the given target", action="append")

	(options, args) = parser.parse_args()

	if options.verbose:
		log_level = logging.INFO
	else:
		log_level = logging.WARNING

	logging.basicConfig(level=log_level)

	did_anything = False

	if options.print_dependencies:
		[print_dependencies(x) for x in args]
		did_anything = True

	if options.projects is not None:
		did_anything = True
		
		if not options.xcode_version:
			f=os.popen("xcodebuild -version")
			xcodebuild_version = f.readlines()[0]
			match = re.search('Xcode ([a-zA-Z0-9.]+)', xcodebuild_version)
			if match:
				(options.xcode_version, ) = match.groups()
		
		for name in options.projects:
			project = Pbxproj.get_pbxproj_by_name(name, xcode_version = options.xcode_version)
			add_modules_to_project(args, project, options.configs)

	if not did_anything:
		parser.print_help()

if __name__ == "__main__":
	sys.exit(main())
