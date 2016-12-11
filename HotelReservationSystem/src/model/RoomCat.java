package model;

public class RoomCat {
	private int rooms_category_id;
	private int hotel_id;
	private String category_name;
	private String category_description;
	private String category_image;
	
	public int getRooms_category_id() {
		return rooms_category_id;
	}
	public void setRooms_category_id(int rooms_category_id) {
		this.rooms_category_id = rooms_category_id;
	}
	public String getCategory_image() {
		return category_image;
	}
	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
}
