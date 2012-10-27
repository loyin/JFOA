function JwFlvplayer(flvurl,width,height,todivid){
	var so = new SWFObject(path+'/flash/jwflvplayer.swf','mpl',width,height,'9');
	so.addParam('allowfullscreen','true');
	so.addParam('allowscriptaccess','always');
	so.addParam('wmode','transparent');
	so.addVariable('file',path+flvurl);
	so.addVariable('autostart','true');
	so.addVariable('controlbar','none');//控制栏
	so.addVariable('repeat','always');//单个重复播放
	//so.addVariable('repeat','list');//播放列表重复播放
	so.write(todivid);
}
function Flvplayer(flvurl,width,height,todivid){
	var so = new SWFObject(path+'/flash/Flvplayer.swf','mpl',width,height,'9');
	so.addParam('allowFullScreen','true');
	so.addParam('wmode','transparent');
	so.addVariable('vcastr_file',flvurl);
	so.addVariable('IsAutoPlay','0');
	so.addVariable('LogoUrl',path+'/image/logo_h_min.png');//添加播放器水印 不会对视频添加水印
	if(flvurl!="")
	so.write(todivid);
}
function showFoucePic(picurl,pictext,piclink,pwidth,pheight,todivid,textheight){
	var so = new SWFObject(path+"/flash/focus.swf", todivid, pwidth,pheight, "9");
	so.addParam('wmode','transparent');
	so.addVariable("picurl",picurl);
	so.addVariable("pictext",pictext);
	so.addVariable("piclink",piclink);
	so.addVariable("pictime","3");
	so.addVariable("borderwidth",pwidth);
	so.addVariable("borderheight",pheight);
	so.addVariable("borderw","false");
	so.addVariable("buttondisplay","true");
	so.addVariable("textheight",textheight);
	so.write(todivid);
}
function isEmail(strEmail) { 
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) 
	return true; 
	else 
	return false; 
}
/** 
 *  URL encode / decode 
 *  http://www.webtoolkit.info/ 
 * 注意，使用方法 Url.encode(string) 得到的是UTF-8编码的数据 
 **/
var Url = {
	// public method for url encoding 
	encode : function(string) {
		return escape(this._utf8_encode(string));
	},

	// public method for url decoding 
	decode : function(string) {
		return this._utf8_decode(unescape(string));
	},

	// private method for UTF-8 encoding 
	_utf8_encode : function(string) {
		string = string.replace(/\r\n/g, "\n");
		var utftext = "";

		for ( var n = 0; n < string.length; n++) {

			var c = string.charCodeAt(n);

			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
		}
		return utftext;
	},
	// private method for UTF-8 decoding 
	_utf8_decode : function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while (i < utftext.length) {

			c = utftext.charCodeAt(i);

			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
};
$.ajaxSetup({cache:false});
//xheditor配置
var uploadUrl="file/up";
var xheditor_={tools:'Cut,Copy,Paste,Pastetext,|,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,SelectAll,Removeformat,|,Align,List,Outdent,Indent,|,Link,Unlink,Anchor,Img,Flash,Flv,Media,Hr,Emot,Table,|,Preview,Print,Fullscreen'};
var upLinkExt_="zip,rar,7z,txt,doc,docx,xls,xlsx,pdf,chm,ppt,pptx";
var upImgExt_="jpg,jpeg,gif,png,bmp";
var xheditor_mini={tools:"Bold,Italic,Underline,Strikethrough,|,Fontface,FontSize,FontColor,BackColor,|,Emot,Img,Flash,Flv,Link"};
var xheditor_ajaxupload_={tools:xheditor_.tools,upLinkUrl:"file/up",upLinkExt:upLinkExt_,
	upImgUrl:uploadUrl,upImgExt:upImgExt_,
	upFlashUrl:uploadUrl,upFlashExt:"swf",
	upFlvUrl:uploadUrl,upFlvExt:"flv",
	upMediaUrl:uploadUrl,upMediaExt:"avi",html5Upload:false};//html5Upload关闭后才能正常上传 ,upMultiple:5
var xheditor_ajaxupload_mini={tools:xheditor_mini.tools,upLinkUrl:uploadUrl,upLinkExt:upLinkExt_,
		upImgUrl:uploadUrl,upImgExt:upImgExt_,
		upFlashUrl:uploadUrl,upFlashExt:"swf",
		upFlvUrl:uploadUrl,upFlvExt:"flv",html5Upload:false};
function initQueryForm(){
	$("select[val]").each(function(i){
		var $this=$(this);
		$this.find("option").attr("selected","");
		var val=$this.attr("val");
		if(val!=""){
			$this.find("option[value='"+val+"']").attr("selected","selected");
		}else{
			$("option ",$this).eq(0).attr("selected","selected");
		}
		$this.change();
		});
	$(":input[val]").each(function(i){
		var $this=$(this);
		var val=$this.attr("val");
		if(val!="")
		$this.val($this.attr("val"));
		});
	$(".searchBar [type='submit']").click(function(){
		$("[name='pageNum']").val(1);
	});
	$("textarea[val]").each(function(i){
		var $this=$(this);
		var val=$this.attr("val");
		if(val!=""){
			$this.html(val);
		}
		});
	$(".searchContent :text").each(function(i,item){
		$(item).css("width","75px");
	});
}
