package rooms.category;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import account.dbOperation.RoomsDBOperation;
import model.RoomCat;

@WebServlet("/addRoomsCategory")
//@MultipartConfig(location="/uploads",maxFileSize=1024*1024*5)

public class AddRoomsCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomCat roomCat;
	String fname;
	public AddRoomsCategory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RoomsDBOperation roomsDBOperation = new RoomsDBOperation();
	roomsDBOperation.listHotels(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter( );
		 
		 try {
		 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		  if (!isMultipart) {
		  out.println("File Not Uploaded");
		  }else{
			  
		  FileItemFactory factory = new DiskFileItemFactory();
		  ServletFileUpload upload = new ServletFileUpload(factory);
		  
		  List items = null;
		  items = upload.parseRequest(request);
		  //System.out.println("items: "+items);
		 
		  Iterator itr = items.iterator();
		  
		  roomCat = new RoomCat();//model
		  while (itr.hasNext()) {
		  FileItem item = (FileItem) itr.next();		  
		  if (item.isFormField()){			  
		  String name = item.getFieldName();	//html input field name
		  String value = item.getString();		//html input field value
		  
			  if(name.equals("hotels_id")){
				  roomCat.setHotel_id(Integer.parseInt(value));  
			 //out.println(roomCat.getHotel_id()+" id");
			  }
			  if(name.equals("category_name")){
				  roomCat.setCategory_name(value);
			 //out.println(roomCat.getCategory_name()+" name");
			  }
			  if(name.equals("category_description")){
				  roomCat.setCategory_description(value);  
			 //out.println(roomCat.getCategory_description()+" desc");
			  }
		  }else{
		  String itemName = item.getName(); 	//file with full path
		  fname = new File(itemName).getName();	//file name only  
		  roomCat.setCategory_image(fname);
		  //File savedFile = new File("F:/ProgramFiles/eclipseProject/HotelReservationSystem/WebContent/uploads/"+fname);
		  //item.write(savedFile);
		  
		  String root = getServletContext().getRealPath("/");
		  File path = new File(root + "/images");
		  if (!path.exists()) {
		      path.mkdirs();
		  }

		  File uploadedFile = new File(path + File.separator + fname);
		  item.write(uploadedFile);
		  		  
		  }
		  }
		  }
		  }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 //////////////////////////////upload in database //////////////////////////////////////
		  RoomsDBOperation roomsDBOperation = new RoomsDBOperation();
		 roomsDBOperation.uploadRoomCategory(roomCat,request,response);
		   ///////////////////////////////////////////////////////////////////////////////////////
		 }
}