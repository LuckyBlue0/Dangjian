package cn.com.do1.component.photowall.photowall.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.common.util.FileUtil;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.photowall.photowall.dao.IPhotowallDAO;
import cn.com.do1.component.photowall.photowall.model.TbPhotoWallImgPO;
import cn.com.do1.component.photowall.photowall.model.TbPhotoWallPO;
import cn.com.do1.component.photowall.photowall.model.TbPhotoWallReviewPO;
import cn.com.do1.component.photowall.photowall.service.IPhotowallService;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallDetailsResponse;
import cn.com.do1.component.photowall.photowall.vo.PhotowallTypeRequest;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallImgVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallReviewVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallVO;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: tanshaoqi
*/
@Service("photowallService")
public class PhotowallServiceImpl extends BaseService implements IPhotowallService {
    private final static transient Logger logger = LoggerFactory.getLogger(PhotowallServiceImpl .class);

    private IPhotowallDAO photowallDAO;
    
    @Resource
    public void setPhotowallDAO(IPhotowallDAO photowallDAO) {
        this.photowallDAO = photowallDAO;
        setDAO(photowallDAO);
    }

	@Override 
    public Pager searchTbPhotoWall(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return photowallDAO .searchTbPhotoWall(searchMap, pager);
    }
    
    @Override
    public List<TbPhotoWallVO> searchTbPhotoWallList(Map<String, Object> searchValue) throws SQLException {
        return photowallDAO .searchTbPhotoWallList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbPhotoWallPO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        photowallDAO .delete(po);
    }

    @Override
    public void casAdd(TbPhotoWallPO po) throws Exception, DataConfictException {
        photowallDAO .insert(po);
    }

    @Override 
    public List<TbPhotoWallPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return photowallDAO .searchByField(TbPhotoWallPO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbPhotoWallPO po = new TbPhotoWallPO();
        po._setPKValue(id);
        photowallDAO .delete(po);
    }

	@Override
	public void pushPhotowall(PhotowallTypeRequest request) throws Exception, BaseException {
		// TODO Auto-generated method stub
		TbPhotoWallPO po = new TbPhotoWallPO();
		
		//留影墙信息
		String id = UUID.randomUUID().toString().toLowerCase();
		po.setId(id);
		po.setCreateTime(new Date());
		po.setCreateUserId(request.getUserId());
		po.setStatus("0");
		po.setTitle(request.getTitle());
		po.setType(request.getType());
		po.setDes(request.getDesc());
		this.photowallDAO.insertData(po);
		
		//图片信息
		String [] photos = request.getPhotos();
		if(!AssertUtil.isEmpty(photos)){
			TbPhotoWallImgPO imgPO = null;
			for(int i=0;i<photos.length;i++){
				imgPO = new TbPhotoWallImgPO();
				imgPO.setId(UUID.randomUUID().toString().toLowerCase());
				imgPO.setPhotoWallId(id);
				imgPO.setSort((i+1)+"");
				imgPO.setImgUrl(fileUpload(photos[i],"upload/photowall/image"));
				this.photowallDAO.insertData(imgPO);
			}
		}
	}
	
	/**
	 * 保存文件
	 * 
	 * @param portraitPic
	 * @throws Exception
	 */
	private String fileUpload(String portraitPic, String baseDir) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		StringBuilder dir = new StringBuilder();
		if ((baseDir.endsWith("/")) || (baseDir.endsWith("\\"))) {
			// dir.append(DateTimeUtil.date2String(date, "yyyyMMdd"));
		} else {
			// dir.append(File.separator);
			// dir.append(DateTimeUtil.date2String(date, "yyyyMMdd"));
		}

		File dirFile = new File(baseDir + dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		StringBuilder fileStr = null;
		fileStr = new StringBuilder(dir.toString());
		// fileStr.append(File.separator);
		String newFileName = UUID.randomUUID().toString()+".jpg";
		fileStr.append(newFileName);
		File file = new File(baseDir + fileStr.toString());
		if (file.exists()) {
			// continue;
		}

		byte[] byteArray = Base64.decodeBase64(portraitPic);
		ByteArrayInputStream is = null;
		FileOutputStream fos = null;
		boolean noError = true;
		String msg = "";
		try {
			file.createNewFile();
			is = new ByteArrayInputStream(byteArray);
			fos = new FileOutputStream(file);
			byte[] bufferArray = new byte[1024];
			while (is.read(bufferArray) != -1)
				fos.write(bufferArray);
		} catch (Exception e) {
			this.logger.error("保存图片发生错误", e);
			noError = false;
			msg = e.getMessage();
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e1) {
				noError = false;
				msg = e.getMessage();
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception e2) {
				noError = false;
				msg = e.getMessage();
			}
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				noError = false;
				msg = e.getMessage();
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
				noError = false;
				msg = e.getMessage();
			}
		}
		if (!noError) {
			if (file.exists()) {
				file.delete();
			}
			throw new Exception("保存文件发生异常,异常提示:" + msg);
		}
		String backURL = "";
		FileUploadUtil up = new FileUploadUtil();
		backURL = FileUploadUtil.uploadFileBySMB(file, "upload/photowall/image/"+DateUtil.formatCurrent("yyyyMM"), newFileName);
		//复制一个命名规范的图片文件在本地
		File newFile = FileUtil.copy(file, file.getParent()+File.separator+newFileName, false);
		//按比例压缩,压缩后及时删除本地文件
		ImageUtil.resetSize(newFile, ConfigMgr.get("news", "middle", "_middle"),"upload/photowall/image/"+DateUtil.formatCurrent("yyyyMM"), true);
		return backURL;
	}

	@Override
	public PhotoWallDetailsResponse findPhotoWallById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		PhotoWallDetailsResponse response = this.photowallDAO.findPhotoWallById(id);
		List<TbPhotoWallImgVO> imgs = this.photowallDAO.findPhotowallImgsById(id);
		if(!AssertUtil.isEmpty(imgs)){
			response.setImgs(imgs);
		}
		return response;
	}

	@Override
	public Pager searchTbPhotoWallReview(Map searchValue, Pager pager) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return this.photowallDAO.searchTbPhotoWallReview(searchValue,pager);
	}

	@Override
	public void photowallReview(PhotowallTypeRequest request) throws Exception, BaseException {
		// TODO Auto-generated method stub
		TbPhotoWallReviewPO po = new TbPhotoWallReviewPO();
		
		//留影墙评论信息
		String id = UUID.randomUUID().toString().toLowerCase();
		po.setId(id);
		po.setPhotoWallId(request.getId());
		po.setTime(new Date());
		po.setUserId(request.getUserId());
		po.setStatus("0");
		po.setContent(request.getContent());
		String [] photos = request.getPhotos();
		if(!AssertUtil.isEmpty(photos)){
			//目前只上传一张图片
			po.setImgUrl(fileUpload(photos[0],"upload/photowall/image"));
		}
		this.photowallDAO.insertData(po);
	}

	@Override
	public TbPhotoWallVO findPhotoWallVOById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		TbPhotoWallVO vo = this.photowallDAO.findPhotoWallVOById(id);
		if(!AssertUtil.isEmpty(vo)){
			List<TbPhotoWallImgVO> imgs = this.photowallDAO.findPhotowallImgsById(id);
			if(!AssertUtil.isEmpty(imgs)){
				String [] imgArr = new String[imgs.size()];
				for(int i=0;i<imgs.size();i++){
					TbPhotoWallImgVO img = imgs.get(i);
					if(!AssertUtil.isEmpty(img) && !AssertUtil.isEmpty(img.getImgUrl())){
						imgArr[i] = img.getImgUrl();
					}
				}
				vo.setImgs(imgArr);
			}
		}
		return vo;
	}

	@Override
	public ArrayList<TbPhotoWallReviewVO> findPhotowallListById(String id) throws Exception, BaseException {
		// TODO Auto-generated method stub
		return this.photowallDAO.findPhotowallListById(id);
	}
}
