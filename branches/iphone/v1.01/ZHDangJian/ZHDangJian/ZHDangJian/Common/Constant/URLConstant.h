/**
 *
 * 有关URL常量的定义
 *
 */


//#ifdef Debug
//static NSString *const KURL = @"http://...";
//#endif
//#ifdef Relese
//static NSString *const KURL = @"http://...";
//#endif

// 外网
static NSString *const KURL = @"http://183.63.138.178:2013/zhdj/interface";
static NSString *const KImageURL = @"http://183.63.138.178:2013/zhdj/";
static NSString *const KWebViewUrl = @"http://183.63.138.178:2013/zhdj";

// 内网
//static NSString *const KURL = @"http://192.168.1.48:2013/zhdj/interface";
//static NSString *const KImageURL = @"http://192.168.1.48:2013/zhdj/";
//static NSString *const KWebViewUrl = @"http://192.168.1.48:2013/zhdj";

/**
 * URL链接
 */

// 登录接口
static NSString *const KLoginUrlFormat = @"%@/userinfo/userinfo!login.action?requestJson=%@";

// 设备安装记录接口
static NSString *const KRecordDeviceUrlFormat = @"%@/install/install!installRecord.action?requestJson=%@";

// 最新资讯查询接口
static NSString *const KNewsInfoUrlFormat = @"%@/news/newsinfo!indexNewsInfo.action?requestJson=";

// 资讯列表接口
static NSString *const KNewsListUrlFormat = @"%@/news/newsinfo!newsInfoList.action?requestJson=%@";

// 资讯详情接口
static NSString *const KNewsDetailUrlFormat = @"%@/news/newsinfo!newsInfoDetails.action?requestJson=%@";

// 个人信息查询接口
static NSString *const KMyUserInfoUrlFormat = @"%@/userinfo/userinfo!myUserInfo.action?requestJson=%@";

// 个人信息编辑接口
static NSString *const KEditPersonalInfoUrlFormat = @"%@/userinfo/userinfo!modifyUserInfo.action?requestJson=%@";

// 积分明细列表接口
static NSString *const KScoreDetailUrlFormat = @"%@/userinfo/userinfo!rankingDetailsList.action?requestJson=%@";

// 积分排名列表接口
static NSString *const KRankListUrlFormat = @"%@/userinfo/userinfo!integralRanking.action?requestJson=%@";

// 修改密码接口
static NSString *const KModifyPwdUrlFormat = @"%@/userinfo/userinfo!modifyUserPwd.action?requestJson=%@";

// 三会一课列表接口
static NSString *const KMeetingListUrlFormat = @"%@/partywork/partywork!partyMeetingList.action?requestJson=%@";

// 支部活动列表接口
static NSString *const KBranchActiveListUrlFormat = @"%@/partywork/partywork!branchActivityList.action?requestJson=%@";

// 志愿活动列表接口
static NSString *const KWishActiveListUrlFormat = @"%@/partywork/partywork!volunteerActivityList.action?requestJson=%@";

// 民主生活会列表接口
static NSString *const KLifestyleListUrlFormat = @"%@/partywork/partywork!democrticLifeList.action?requestJson=%@";

// 活动心得列表接口
static NSString *const KActivityStudyListUrlFormat = @"%@/activityTips/activityTips!tipsList.action?requestJson=%@";

// 活动心得提交接口
static NSString *const KSubmitStudyUrlFormat = @"%@/activityTips/activityTips!saveTips.action?requestJson=%@";

// 三会一课/支部活动/民主生活会/志愿活动详情接口
static NSString *const KActivityDetailUrlFormat = @"%@/partywork/partywork!workDetails.action?requestJson=%@";

// 报名或取消报名接口
static NSString *const KSignUpOrNotUrlFormat = @"%@/partywork/partywork!signUp.action?requestJson=%@";

// 请假或取消请假接口
static NSString *const KLeaveOrNotUrlFormat = @"%@/partywork/partywork!askForLeave.action?requestJson=%@";

// 二维码签到接口
static NSString *const KSignUrlFormat = @"%@/partywork/partywork!signIn.action?requestJson=%@";

// 民主机关评议主题列表接口
static NSString *const KGovernDiscussListUrlFormat = @"%@/orgvote/orgvoteInterfaceAction!orgVoteList.action?requestJson=%@";

// 民主机关评议被评议对象列表接口
static NSString *const KDiscussededMemListUrlFormat = @"%@/orgvote/orgvoteInterfaceAction!appraisalOrgVoteList.action?requestJson=%@";

// 查看民主机关评议投票内容
static NSString *const KGovernVoteContentUrlFormat = @"%@/orgvote/orgvoteInterfaceAction!getVoteContent.action?requestJson=%@";

// 民主机关评议投票接口
static NSString *const KGovDiscussVoteUrlFormat = @"%@/orgvote/orgvoteInterfaceAction!addVoteResult.action?requestJson=%@";

// 优秀党员评议主题列表接口
static NSString *const KPartyMemThemeListUrlFormat = @"%@/partymembervote/partymembervoteInterfaceAction!partymemberVoteList.action?requestJson=%@";

// 优秀党员列表接口
static NSString *const KExcellentPartyMemListUrlFormat = @"%@/partymembervote/partymembervoteInterfaceAction!votePartyMemberList.action?requestJson=%@";

// 优秀党员信息接口
static NSString *const KExcellentPartyMemInfoUrlFormat = @"%@/partymembervote/partymembervoteInterfaceAction!viewPartyMember.action?requestJson=%@";

// 优秀党员评议投票接口
static NSString *const KPartyMemVoteUrlFormat = @"%@/partymembervote/partymembervoteInterfaceAction!addVoteResult.action?requestJson=%@";

// 优秀党员评议结果列表接口
static NSString *const KExcellentMemListUrlFormat = @"%@/partymembervote/partymembervoteInterfaceAction!searchVoteResult.action?requestJson=%@";

// 意见反馈提交接口
static NSString *const KFeedBackUrlFormat = @"%@/suggestion/suggestion!addSuggestion.action?requestJson=%@";
