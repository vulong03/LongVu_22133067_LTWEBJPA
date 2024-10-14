package iostar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import iostar.dao.impl.CategoryDao;
import iostar.entity.Category;
//import iostar.models.CategoryModel;
import iostar.services.ICategoryService;
import iostar.services.impl.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 , maxFileSize =1024 *1024*5, maxRequestSize =1024*1024*5*5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert" ,"/admin/category/edit","/admin/category/update","/admin/category/delete","/admin/category/search"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		else if (url.contains("edit")) {
			int id =Integer.parseInt(req.getParameter("id"));
			
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if(url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				cateService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			//String images = "https://cdn.tgdd.vn/Products/Images/42/260546/oppo-reno8-pro-thumb-xanh-1-600x600.jpg";
			Category category = new Category();
			category.setCategoryname(categoryname);
			String fname="";
			String uploadPath ="C:\\upload";
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part =req.getPart("images");
				if(part.getSize() >0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index =filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					category.setImages(fname);
				}
				else {
					category.setImages("avata.png");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			category.setImages(fname);
			category.setStatus(statuss);
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}else if(url.contains("update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			//String images = "https://cdn.tgdd.vn/Products/Images/42/260546/oppo-reno8-pro-thumb-xanh-1-600x600.jpg";
			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			//category.setImages(images);
			//luu hinh cu
			Category cateold =cateService.findById(categoryid);
			String fileold = cateold.getImages();
			// xu ly image
			String fname="";
			String uploadPath ="C:\\upload";
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part =req.getPart("images");
				if(part.getSize() >0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index =filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					category.setImages(fname);
				}
				else {
					category.setImages(fileold);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			category.setStatus(statuss);
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}

	}
}
