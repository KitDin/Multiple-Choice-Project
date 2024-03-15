/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CauHoi;

public class Questions {
    private int CH_Id;
    private String HP_Id,MD_TenMucDo,CH_CauHoi,CH_DapAn1,CH_DapAn2,CH_DapAn3,CH_DapAn4,DA_DapAnDung;
    
    public Questions(){};
    
    public Questions(int CH_Id,String HP_Id,String MD_TenMucDo,String CH_CauHoi,String CH_DapAn1,String CH_DapAn2,String CH_DapAn3,String CH_DapAn4,String DA_DapAnDung){
        this.CH_Id = CH_Id;
        this.HP_Id = HP_Id;
        this.MD_TenMucDo = MD_TenMucDo;
        this.CH_CauHoi = CH_CauHoi;
        this.CH_DapAn1 = CH_DapAn1;
        this.CH_DapAn2 = CH_DapAn2;
        this.CH_DapAn3 = CH_DapAn3;
        this.CH_DapAn4 = CH_DapAn4;
        this.DA_DapAnDung = DA_DapAnDung;
    }

    @Override
    public String toString() {
        return "Questions{" + "CH_Id=" + CH_Id + ", HP_Id=" + HP_Id + ", MD_TenMucDo=" + MD_TenMucDo + ", CH_CauHoi=" + CH_CauHoi + ", CH_DapAn1=" + CH_DapAn1 + ", CH_DapAn2=" + CH_DapAn2 + ", CH_DapAn3=" + CH_DapAn3 + ", CH_DapAn4=" + CH_DapAn4 + ", DA_DapAnDung=" + DA_DapAnDung + '}';
    }

    public int getCH_Id() {
        return CH_Id;
    }

    public String getHP_Id() {
        return HP_Id;
    }

    public String getMD_TenMucDo() {
        return MD_TenMucDo;
    }

    public String getCH_CauHoi() {
        return CH_CauHoi;
    }

    public String getCH_DapAn1() {
        return CH_DapAn1;
    }

    public String getCH_DapAn2() {
        return CH_DapAn2;
    }

    public String getCH_DapAn3() {
        return CH_DapAn3;
    }

    public String getCH_DapAn4() {
        return CH_DapAn4;
    }

    public String getDA_DapAnDung() {
        return DA_DapAnDung;
    }

    
    
    
}
