package com.beta.rsatech.churchcradle.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.beta.rsatech.churchcradle.server.utils.MiscStorage;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@SuppressWarnings("serial")
public class BlobstorePreviewServlet extends HttpServlet{

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("image");
		
		
		
		if(blobKeys != null){
			String blobKeyString = blobKeys.get(0).getKeyString();
			
			BlobKey blobKey = new BlobKey(blobKeyString);
			blobstoreService.serve(blobKey, resp);
		}
	
		//super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		final String SCALE_350_PX = "=s350";
		
//		FileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		
//		List<FileItem> items = null;
//		try {
//			items = upload.parseRequest(req);
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//		}
//		
//		String image64 = null;
//		
//		for(FileItem item : items){
//			if(!item.isFormField()){
//				 image64 = Base64.encode(item.get());
//				 System.out.println("Base64 => "+image64);
//			}
//		}
		
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("image");
		
		for(BlobKey blob : blobKeys){
			System.out.println("Base64 => "+Base64.encode(blobstoreService.fetchData(blob, 0, blobstoreService.MAX_BLOB_FETCH_SIZE - 1)));
		}
		
//		if(blobKeys != null){
//			ImagesService imagesService = ImagesServiceFactory.getImagesService();
//			String blobKeyString = blobKeys.get(0).getKeyString();
//			BlobKey blobKey = new BlobKey(blobKeyString);
//			
//			String url = imagesService.getServingUrl(blobKey)+SCALE_350_PX;
//			
//			System.out.println("blobKey => "+blobKeyString+" img => "+url);
//
//			MiscStorage.getInstance().setImgBlobKey(blobKey);
//			MiscStorage.getInstance().setImgUrl(url);
//			
//			resp.getWriter().print("s");
//			resp.flushBuffer();
//
//		}
	}
}
