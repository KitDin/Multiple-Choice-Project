/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoVien;

/**
 *
 * @author DELL
 */
public class GIAO_VIENs {

    private int GV_Id;
    private String GV_Hoten, GV_Taikhoan, GV_Matkhau;

    public GIAO_VIENs() {
    }

    public GIAO_VIENs(int GV_Id, String GV_Hoten, String GV_Taikhoan, String GV_Matkhau) {
        this.GV_Hoten = GV_Hoten;
        this.GV_Id = GV_Id;
        this.GV_Matkhau = GV_Matkhau;
        this.GV_Taikhoan = GV_Taikhoan;
    }

    public int getGV_Id() {
        return GV_Id;
    }

    public String getGV_Hoten() {
        return GV_Hoten;
    }

    public String getGV_Taikhoan() {
        return GV_Taikhoan;
    }

    public String getGV_Matkhau() {
        return GV_Matkhau;
    }

}
