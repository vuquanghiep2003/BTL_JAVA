package quanlynhansu.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tinh {
    private int maTinh;
    private  String tenTinh;

    public Tinh(int maTinh, String tenTinh){
        this.maTinh = maTinh;
        this.tenTinh = tenTinh;
    }
    public int getMaTinh(){
        return  maTinh;
    }
    public void setMaTinh(int maTinh){
        this.maTinh = maTinh;
    }

    public String getTenTinh(){
        return  tenTinh;
    }

    public void setTenTinh(String tenTinh){
        this.tenTinh = tenTinh;
    }

    public String toString(){
        return "Tinh [Mã Tinh=" +tenTinh+", Ten Tinh="+maTinh+"]";
    }

    public  boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() !=obj.getClass())
            return false;
        Tinh other = (Tinh) obj;
        return maTinh == other.maTinh && Objects.equals(tenTinh, other.tenTinh);
    }

    public static ArrayList<Tinh> getDSTinh(){
        String[] arr_tinh = {"An Giang",
                "Bà Rịa – Vũng Tàu",
                "Bắc Giang",
                "Bắc Kạn",
                "Bạc Liêu",
                "Bắc Ninh",
                "Bến Tre",
                "Bình Định",
                "Bình Dương",
                "Bình Phước",
                "Bình Thuận",
                "Cà Mau",
                "Cần Thơ",
                "Cao Bằng",
                "Đà Nẵng",
                "Đắk Lắk",
                "Đắk Nông",
                "Điện Biên",
                "Đồng Nai",
                "Đồng Tháp",
                "Gia Lai",
                "Hà Giang",
                "Hà Nam",
                "Hà Nội",
                "Hà Tĩnh",
                "Hải Dương",
                "Hải Phòng",
                "Hậu Giang",
                "Hòa Bình",
                "Hưng Yên",
                "Khánh Hòa",
                "Kon Tum",
                "Lai Châu",
                "Lâm Đồng",
                "Lạng Sơn",
                "Lào Cai",
                "Long An",
                "Nam Định",
                "Nghệ An",
                "Ninh Bình",
                "Ninh Thuận",
                "Phú Thọ",
                "Phú Yên",
                "Quảng Bình",
                "Quảng Nam",
                "Quảng Ngãi",
                "Quảng Ninh",
                "Quảng Trị",
                "Sóc Trăng",
                "Sơn La",
                "Tây Ninh",
                "Thái Bình",
                "Thái Nguyên",
                "Thanh Hóa",
                "Thừa Thiên Huế",
                "Tiền Giang",
                "TP Hồ Chí Minh",
                "Trà Vinh",
                "Tuyên Quang",
                "Vĩnh Long",
                "Vĩnh Phúc",
                "Yên Bái"
        };
        ArrayList<Tinh> listTinh = new ArrayList<Tinh>();
        int i=0;
        for (String tenTinh : arr_tinh ) {
            Tinh t = new Tinh(i, tenTinh);
            listTinh.add(t);
        }
        return listTinh;
    }
}