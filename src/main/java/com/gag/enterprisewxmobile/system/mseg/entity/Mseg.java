package com.gag.enterprisewxmobile.system.mseg.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Mseg)实体类
 *
 * @author makejava
 * @since 2020-11-19 15:57:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mseg extends BaseRowModel implements Serializable  {
    private static final long serialVersionUID = 171912417692942545L;

    @ExcelProperty(value = "autoid",index = 0)
    private Integer autoid;
    @ExcelProperty(value = "mandt",index = 1)
    private String mandt;
    @ExcelProperty(value = "mblnr",index = 2)
    private String mblnr;
    @ExcelProperty(value = "mjahr",index = 3)
    private String mjahr;
    @ExcelProperty(value = "bwart",index = 4)
    private String bwart;
    @ExcelProperty(value = "matnr",index = 5)
    private String matnr;
    @ExcelProperty(value = "insmk",index = 6)
    private String insmk;
    @ExcelProperty(value = "sobkz",index = 7)
    private String sobkz;
    @ExcelProperty(value = "lifnr",index = 8)
    private String lifnr;
    @ExcelProperty(value = "kunnr",index = 9)
    private String kunnr;
    @ExcelProperty(value = "kdauf",index = 10)
    private String kdauf;
    @ExcelProperty(value = "shkzg",index = 11)
    private String shkzg;
    @ExcelProperty(value = "waers",index = 12)
    private String waers;
    @ExcelProperty(value = "dmbtr",index = 13)
    private Double dmbtr;
    @ExcelProperty(value = "bnbtr",index = 14)
    private Double bnbtr;
    @ExcelProperty(value = "bualt",index = 15)
    private Double bualt;
    @ExcelProperty(value = "shkum",index = 16)
    private String shkum;
    @ExcelProperty(value = "meins",index = 17)
    private String meins;
    @ExcelProperty(value = "erfmg",index = 18)
    private Double erfmg;
    @ExcelProperty(value = "erfme",index = 19)
    private String erfme;
    @ExcelProperty(value = "bpmng",index = 20)
    private Double bpmng;
    @ExcelProperty(value = "bprme",index = 21)
    private String bprme;
    @ExcelProperty(value = "ebeln",index = 22)
    private String ebeln;
    @ExcelProperty(value = "ebelp",index = 23)
    private String ebelp;
    @ExcelProperty(value = "lfbja",index = 24)
    private String lfbja;
    @ExcelProperty(value = "lfbnr",index = 25)
    private String lfbnr;
    @ExcelProperty(value = "lfpos",index = 26)
    private String lfpos;
    @ExcelProperty(value = "sgtxt",index = 27)
    private String sgtxt;
    @ExcelProperty(value = "wempf",index = 28)
    private String wempf;
    @ExcelProperty(value = "ablad",index = 29)
    private String ablad;
    @ExcelProperty(value = "parbu",index = 30)
    private String parbu;
    @ExcelProperty(value = "kostl",index = 31)
    private String kostl;
    @ExcelProperty(value = "gjahr",index = 32)
    private String gjahr;
    @ExcelProperty(value = "bukrs",index = 33)
    private String bukrs;
    @ExcelProperty(value = "buzei",index = 34)
    private String buzei;
    @ExcelProperty(value = "ummat",index = 35)
    private String ummat;
    @ExcelProperty(value = "umwrk",index = 36)
    private String umwrk;
    @ExcelProperty(value = "umlgo",index = 37)
    private String umlgo;
    @ExcelProperty(value = "umcha",index = 38)
    private String umcha;
    @ExcelProperty(value = "umsok",index = 39)
    private String umsok;
    @ExcelProperty(value = "kzbew",index = 40)
    private String kzbew;
    @ExcelProperty(value = "kstrg",index = 41)
    private String kstrg;
    @ExcelProperty(value = "prctr",index = 42)
    private String prctr;
    @ExcelProperty(value = "aufps",index = 43)
    private String aufps;
    @ExcelProperty(value = "bstmg",index = 44)
    private Double bstmg;
    @ExcelProperty(value = "bstme",index = 45)
    private String bstme;
    @ExcelProperty(value = "emlif",index = 46)
    private String emlif;
    @ExcelProperty(value = "exbwr",index = 47)
    private Double exbwr;
    @ExcelProperty(value = "vkwrt",index = 48)
    private Double vkwrt;
    @ExcelProperty(value = "xbeau",index = 49)
    private String xbeau;
    @ExcelProperty(value = "lsmng",index = 50)
    private Double lsmng;
    @ExcelProperty(value = "lsmeh",index = 51)
    private String lsmeh;
    @ExcelProperty(value = "kzbws",index = 52)
    private String kzbws;
    @ExcelProperty(value = "qinspst",index = 53)
    private String qinspst;
    @ExcelProperty(value = "urzei",index = 54)
    private String urzei;
    @ExcelProperty(value = "vgartMkpf",index = 55)
    private String vgartMkpf;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ExcelProperty(value = "budatMkpf",index = 56)
    private Date budatMkpf;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ExcelProperty(value = "cpudtMkpf",index = 57)
    private Date cpudtMkpf;

    @ExcelProperty(value = "cputmMkpf",index = 58)
    private String cputmMkpf;

    @ExcelProperty(value = "usnamMkpf",index = 59)
    private String usnamMkpf;
    @ExcelProperty(value = "xblnrMkpf",index = 60)
    private String xblnrMkpf;
    @ExcelProperty(value = "vbelnIm",index = 61)
    private String vbelnIm;
    @ExcelProperty(value = "vbelpIm",index = 62)
    private String vbelpIm;
    @ExcelProperty(value = "aufnr",index = 63)
    private String aufnr;
    @ExcelProperty(value = "menge",index = 64)
    private Double menge;
    @ExcelProperty(value = "werks",index = 65)
    private String werks;
    @ExcelProperty(value = "lgort",index = 66)
    private String lgort;
    @ExcelProperty(value = "sakto",index = 67)
    private String sakto;
    @ExcelProperty(value = "mark",index = 68)
    private Integer mark;
    @ExcelProperty(value = "mid",index = 69)
    private Integer mid;
    @ExcelProperty(value = "iobilln",index = 70)
    private String iobilln;
    @ExcelProperty(value = "iobill",index = 71)
    private String iobill;
    @ExcelProperty(value = "msgbill",index = 72)
    private String msgbill;
    @ExcelProperty(value = "msgbilln",index = 73)
    private String msgbilln;
    @ExcelProperty(value = "msgmark",index = 74)
    private Integer msgmark;
    @ExcelProperty(value = "iotypen",index = 75)
    private String iotypen;
    @ExcelProperty(value = "eventId",index = 76)
    private Integer eventId;
    @ExcelProperty(value = "tf;",index = 77)
    private Integer tf;
    @ExcelProperty(value = "msgbillb",index = 78)
    private String msgbillb;
    @ExcelProperty(value = "msgbillnb",index = 79)
    private String msgbillnb;
    @ExcelProperty(value = "billtype",index = 80)
    private String billtype;
    @ExcelProperty(value = "so",index = 81)
    private String so;
    @ExcelProperty(value = "smbln",index = 82)
    private String smbln;
    @ExcelProperty(value = "smblp",index = 83)
    private String smblp;
    @ExcelProperty(value = "username",index = 84)
    private String username;


}