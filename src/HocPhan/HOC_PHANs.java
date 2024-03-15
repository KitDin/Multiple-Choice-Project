/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HocPhan;

/**
 *
 * @author DELL
 */
public class HOC_PHANs {
    private String HP_Id,HP_TenHocPhan,HKNK_Nam,HKNK_HocKy,Lop_Id;
    
    public  HOC_PHANs(){
        HP_Id = "";
        HP_TenHocPhan= "";
        HKNK_HocKy = "";
        HKNK_Nam = "";
        Lop_Id = "";
    }
    
    public HOC_PHANs(String HP_Id, String HP_TenHocPhan, String HKNK_Nam, String HKNK_HocKy,String Lop_Id){
        this.HP_Id = HP_Id;
        this.HP_TenHocPhan= HP_TenHocPhan;
        this.HKNK_HocKy = HKNK_HocKy;
        this.HKNK_Nam = HKNK_Nam;
        this.Lop_Id = Lop_Id;
    }

    public String getLop_Id() {
        return Lop_Id;
    }

    public String getHP_Id() {
        return HP_Id;
    }

    public String getHP_TenHocPhan() {
        return HP_TenHocPhan;
    }

    public String getHKNK_Nam() {
        return HKNK_Nam;
    }

    public String getHKNK_HocKy() {
        return HKNK_HocKy;
    }
    
    
    
}
