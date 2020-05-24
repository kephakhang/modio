package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_CONFIG")
public class Config implements Serializable {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  Long id = null ;
	  String title = "" ;
	  String theme = "" ;
	  String admin = "" ;
	  String admin_email = "" ;
	  String admin_email_name = "" ;
	  String add_script = "" ;
	  int use_point = 0 ;
	  int point_term = 0 ;
	  int use_copy_log = 0 ;
	  int use_email_certify = 0 ;
	  int login_point = 0 ;
	  int cut_name = 0 ;
	  int nick_modify = 0 ;
	  String new_skin = "" ;
	  int new_rows = 0 ;
	  String search_skin = "" ;
	  String connect_skin = "" ;
	  String faq_skin = "" ;
	  int read_point = 0 ;
	  int write_point = 0 ;
	  int comment_point = 0 ;
	  int download_point = 0 ;
	  int write_pages = 0 ;
	  int mobile_pages = 0 ;
	  String link_target = "" ;
	  int delay_sec = 0 ;
	  String filter = "" ;
	  String possible_ip = "" ;
	  String intercept_ip = "" ;
	  String analytics = "" ;
	  String add_meta = "" ;
	  String syndi_token = "" ;
	  String syndi_except = "" ;
	  String member_skin = "" ;
	  int use_homepage = 0 ;
	  int req_homepage = 0 ;
	  int use_tel = 0 ;
	  int req_tel = 0 ;
	  int use_hp = 0 ;
	  int req_hp = 0 ;
	  int use_addr = 0 ;
	  int req_addr = 0 ;
	  int use_signature = 0 ;
	  int req_signature = 0 ;
	  int use_profile = 0 ;
	  int req_profile = 0 ;
	  int register_level = 0 ;
	  int register_point = 0 ;
	  int icon_level = 0 ;
	  int use_recommend = 0 ;
	  int recommend_point = 0 ;
	  int leave_day = 0 ;
	  int search_part = 0 ;
	  int email_use = 0 ;
	  int email_wr_super_admin = 0 ;
	  int email_wr_group_admin = 0 ;
	  int email_wr_board_admin = 0 ;
	  int email_wr_write =  0 ;
	  int email_wr_comment_all = 0 ;
	  int email_mb_super_admin = 0 ;
	  int email_mb_member = 0 ;
	  int email_po_super_admin = 0 ;
	  String prohibit_id = "" ;
	  String prohibit_email = "" ;
	  int new_del = 0 ;
	  int memo_del = 0 ;
	  int visit_del = 0 ;
	  int popular_del = 0 ;
	  Date optimize_date = new Date() ;
	  int use_member_icon = 0 ;
	  int member_icon_size = 0 ;
	  int member_icon_width = 0 ;
	  int member_icon_height = 0 ;
	  int login_minutes = 0 ;
	  String image_extension = "" ;
	  String flash_extension = "" ;
	  String movie_extension = "" ;
	  int formmail_is_member = 0 ;
	  int page_rows = 0 ;
	  int mobile_page_rows = 0 ;
	  String visit = "" ;
	  int max_po_id = 0 ;
	  String stipulation = "" ;
	  String privacy = "" ;
	  int open_modify = 0 ;
	  int memo_send_point = 0 ;
	  String mobile_new_skin = "" ;
	  String mobile_search_skin = "" ;
	  String mobile_connect_skin = "" ;
	  String mobile_faq_skin = "" ;
	  String mobile_member_skin = "" ;
	  String captcha_mp3 = "" ;
	  String editor = "" ;
	  int cert_use = 0;
	  String cert_ipin = "" ;
	  String cert_hp = "" ;
	  String cert_kcb_cd = "" ;
	  String cert_kcp_cd = "" ;
	  String lg_mid = "" ;
	  String lg_mert_key = "" ;
	  int cert_limit = 0;
	  int cert_req = 0 ;
	  int sms_use = 0 ;
	  String sms_type = "" ;
	  String icode_id = "" ;
	  String icode_pw = "" ;
	  String icode_server_ip = "" ;
	  String icode_server_port = "" ;
	  String googl_shorturl_apikey = "" ;
	  String facebook_appid = "" ;
	  String facebook_secret = "" ;
	  String twitter_key = "" ;
	  String twitter_secret = "" ;
	  String kakao_js_apikey = "" ;
	  String tmp1_subj = "" ;
	  String tmp2_subj = "" ;
	  String tmp3_subj = "" ;
	  String tmp4_subj = "" ;
	  String tmp5_subj = "" ;
	  String tmp6_subj = "" ;
	  String tmp7_subj = "" ;
	  String tmp8_subj = "" ;
	  String tmp9_subj = "" ;
	  String tmp10_subj = "" ;
	  String tmp1 = "" ;
	  String tmp2 = "" ;
	  String tmp3 = "" ;
	  String tmp4 = "" ;
	  String tmp5 = "" ;
	  String tmp6 = "" ;
	  String tmp7 = "" ;
	  String tmp8 = "" ;
	  String tmp9 = "" ;
	  String tmp10 = "" ;
	  
	  public Config(Long id, String title, String theme, String admin, String admin_email, String admin_email_name,
				String add_script, int use_point, int point_term, int use_copy_log, int use_email_certify, int login_point,
				int cut_name, int nick_modify, String new_skin, int new_rows, String search_skin, String connect_skin,
				String faq_skin, int read_point, int write_point, int comment_point, int download_point, int write_pages,
				int mobile_pages, String link_target, int delay_sec, String filter, String possible_ip, String intercept_ip,
				String analytics, String add_meta, String syndi_token, String syndi_except, String member_skin,
				int use_homepage, int req_homepage, int use_tel, int req_tel, int use_hp, int req_hp, int use_addr,
				int req_addr, int use_signature, int req_signature, int use_profile, int req_profile, int register_level,
				int register_point, int icon_level, int use_recommend, int recommend_point, int leave_day, int search_part,
				int email_use, int email_wr_super_admin, int email_wr_group_admin, int email_wr_board_admin,
				int email_wr_write, int email_wr_comment_all, int email_mb_super_admin, int email_mb_member,
				int email_po_super_admin, String prohibit_id, String prohibit_email, int new_del, int memo_del,
				int visit_del, int popular_del, Date optimize_date, int use_member_icon, int member_icon_size,
				int member_icon_width, int member_icon_height, int login_minutes, String image_extension,
				String flash_extension, String movie_extension, int formmail_is_member, int page_rows, int mobile_page_rows,
				String visit, int max_po_id, String stipulation, String privacy, int open_modify, int memo_send_point,
				String mobile_new_skin, String mobile_search_skin, String mobile_connect_skin, String mobile_faq_skin,
				String mobile_member_skin, String captcha_mp3, String editor, int cert_use, String cert_ipin,
				String cert_hp, String cert_kcb_cd, String cert_kcp_cd, String lg_mid, String lg_mert_key, int cert_limit,
				int cert_req, int sms_use, String sms_type, String icode_id, String icode_pw, String icode_server_ip,
				String icode_server_port, String googl_shorturl_apikey, String facebook_appid, String facebook_secret,
				String twitter_key, String twitter_secret, String kakao_js_apikey, String tmp1_subj, String tmp2_subj,
				String tmp3_subj, String tmp4_subj, String tmp5_subj, String tmp6_subj, String tmp7_subj, String tmp8_subj,
				String tmp9_subj, String tmp10_subj, String tmp1, String tmp2, String tmp3, String tmp4, String tmp5,
				String tmp6, String tmp7, String tmp8, String tmp9, String tmp10) {
			super();
			this.id = id;
			this.title = title;
			this.theme = theme;
			this.admin = admin;
			this.admin_email = admin_email;
			this.admin_email_name = admin_email_name;
			this.add_script = add_script;
			this.use_point = use_point;
			this.point_term = point_term;
			this.use_copy_log = use_copy_log;
			this.use_email_certify = use_email_certify;
			this.login_point = login_point;
			this.cut_name = cut_name;
			this.nick_modify = nick_modify;
			this.new_skin = new_skin;
			this.new_rows = new_rows;
			this.search_skin = search_skin;
			this.connect_skin = connect_skin;
			this.faq_skin = faq_skin;
			this.read_point = read_point;
			this.write_point = write_point;
			this.comment_point = comment_point;
			this.download_point = download_point;
			this.write_pages = write_pages;
			this.mobile_pages = mobile_pages;
			this.link_target = link_target;
			this.delay_sec = delay_sec;
			this.filter = filter;
			this.possible_ip = possible_ip;
			this.intercept_ip = intercept_ip;
			this.analytics = analytics;
			this.add_meta = add_meta;
			this.syndi_token = syndi_token;
			this.syndi_except = syndi_except;
			this.member_skin = member_skin;
			this.use_homepage = use_homepage;
			this.req_homepage = req_homepage;
			this.use_tel = use_tel;
			this.req_tel = req_tel;
			this.use_hp = use_hp;
			this.req_hp = req_hp;
			this.use_addr = use_addr;
			this.req_addr = req_addr;
			this.use_signature = use_signature;
			this.req_signature = req_signature;
			this.use_profile = use_profile;
			this.req_profile = req_profile;
			this.register_level = register_level;
			this.register_point = register_point;
			this.icon_level = icon_level;
			this.use_recommend = use_recommend;
			this.recommend_point = recommend_point;
			this.leave_day = leave_day;
			this.search_part = search_part;
			this.email_use = email_use;
			this.email_wr_super_admin = email_wr_super_admin;
			this.email_wr_group_admin = email_wr_group_admin;
			this.email_wr_board_admin = email_wr_board_admin;
			this.email_wr_write = email_wr_write;
			this.email_wr_comment_all = email_wr_comment_all;
			this.email_mb_super_admin = email_mb_super_admin;
			this.email_mb_member = email_mb_member;
			this.email_po_super_admin = email_po_super_admin;
			this.prohibit_id = prohibit_id;
			this.prohibit_email = prohibit_email;
			this.new_del = new_del;
			this.memo_del = memo_del;
			this.visit_del = visit_del;
			this.popular_del = popular_del;
			this.optimize_date = optimize_date;
			this.use_member_icon = use_member_icon;
			this.member_icon_size = member_icon_size;
			this.member_icon_width = member_icon_width;
			this.member_icon_height = member_icon_height;
			this.login_minutes = login_minutes;
			this.image_extension = image_extension;
			this.flash_extension = flash_extension;
			this.movie_extension = movie_extension;
			this.formmail_is_member = formmail_is_member;
			this.page_rows = page_rows;
			this.mobile_page_rows = mobile_page_rows;
			this.visit = visit;
			this.max_po_id = max_po_id;
			this.stipulation = stipulation;
			this.privacy = privacy;
			this.open_modify = open_modify;
			this.memo_send_point = memo_send_point;
			this.mobile_new_skin = mobile_new_skin;
			this.mobile_search_skin = mobile_search_skin;
			this.mobile_connect_skin = mobile_connect_skin;
			this.mobile_faq_skin = mobile_faq_skin;
			this.mobile_member_skin = mobile_member_skin;
			this.captcha_mp3 = captcha_mp3;
			this.editor = editor;
			this.cert_use = cert_use;
			this.cert_ipin = cert_ipin;
			this.cert_hp = cert_hp;
			this.cert_kcb_cd = cert_kcb_cd;
			this.cert_kcp_cd = cert_kcp_cd;
			this.lg_mid = lg_mid;
			this.lg_mert_key = lg_mert_key;
			this.cert_limit = cert_limit;
			this.cert_req = cert_req;
			this.sms_use = sms_use;
			this.sms_type = sms_type;
			this.icode_id = icode_id;
			this.icode_pw = icode_pw;
			this.icode_server_ip = icode_server_ip;
			this.icode_server_port = icode_server_port;
			this.googl_shorturl_apikey = googl_shorturl_apikey;
			this.facebook_appid = facebook_appid;
			this.facebook_secret = facebook_secret;
			this.twitter_key = twitter_key;
			this.twitter_secret = twitter_secret;
			this.kakao_js_apikey = kakao_js_apikey;
			this.tmp1_subj = tmp1_subj;
			this.tmp2_subj = tmp2_subj;
			this.tmp3_subj = tmp3_subj;
			this.tmp4_subj = tmp4_subj;
			this.tmp5_subj = tmp5_subj;
			this.tmp6_subj = tmp6_subj;
			this.tmp7_subj = tmp7_subj;
			this.tmp8_subj = tmp8_subj;
			this.tmp9_subj = tmp9_subj;
			this.tmp10_subj = tmp10_subj;
			this.tmp1 = tmp1;
			this.tmp2 = tmp2;
			this.tmp3 = tmp3;
			this.tmp4 = tmp4;
			this.tmp5 = tmp5;
			this.tmp6 = tmp6;
			this.tmp7 = tmp7;
			this.tmp8 = tmp8;
			this.tmp9 = tmp9;
			this.tmp10 = tmp10;
		}
	  
	@Override
	public String toString() {
		return "Config [title=" + title + ", theme=" + theme + ", admin=" + admin + ", admin_email=" + admin_email
				+ ", admin_email_name=" + admin_email_name + ", add_script=" + add_script + ", use_point=" + use_point
				+ ", point_term=" + point_term + ", use_copy_log=" + use_copy_log + ", use_email_certify="
				+ use_email_certify + ", login_point=" + login_point + ", cut_name=" + cut_name + ", nick_modify="
				+ nick_modify + ", new_skin=" + new_skin + ", new_rows=" + new_rows + ", search_skin=" + search_skin
				+ ", connect_skin=" + connect_skin + ", faq_skin=" + faq_skin + ", read_point=" + read_point
				+ ", write_point=" + write_point + ", comment_point=" + comment_point + ", download_point="
				+ download_point + ", write_pages=" + write_pages + ", mobile_pages=" + mobile_pages + ", link_target="
				+ link_target + ", delay_sec=" + delay_sec + ", filter=" + filter + ", possible_ip=" + possible_ip
				+ ", intercept_ip=" + intercept_ip + ", analytics=" + analytics + ", add_meta=" + add_meta
				+ ", syndi_token=" + syndi_token + ", syndi_except=" + syndi_except + ", member_skin=" + member_skin
				+ ", use_homepage=" + use_homepage + ", req_homepage=" + req_homepage + ", use_tel=" + use_tel
				+ ", req_tel=" + req_tel + ", use_hp=" + use_hp + ", req_hp=" + req_hp + ", use_addr=" + use_addr
				+ ", req_addr=" + req_addr + ", use_signature=" + use_signature + ", req_signature=" + req_signature
				+ ", use_profile=" + use_profile + ", req_profile=" + req_profile + ", register_level=" + register_level
				+ ", register_point=" + register_point + ", icon_level=" + icon_level + ", use_recommend="
				+ use_recommend + ", recommend_point=" + recommend_point + ", leave_day=" + leave_day + ", search_part="
				+ search_part + ", email_use=" + email_use + ", email_wr_super_admin=" + email_wr_super_admin
				+ ", email_wr_group_admin=" + email_wr_group_admin + ", email_wr_board_admin=" + email_wr_board_admin
				+ ", email_wr_write=" + email_wr_write + ", email_wr_comment_all=" + email_wr_comment_all
				+ ", email_mb_super_admin=" + email_mb_super_admin + ", email_mb_member=" + email_mb_member
				+ ", email_po_super_admin=" + email_po_super_admin + ", prohibit_id=" + prohibit_id
				+ ", prohibit_email=" + prohibit_email + ", new_del=" + new_del + ", memo_del=" + memo_del
				+ ", visit_del=" + visit_del + ", popular_del=" + popular_del + ", optimize_date=" + optimize_date
				+ ", use_member_icon=" + use_member_icon + ", member_icon_size=" + member_icon_size
				+ ", member_icon_width=" + member_icon_width + ", member_icon_height=" + member_icon_height
				+ ", login_minutes=" + login_minutes + ", image_extension=" + image_extension + ", flash_extension="
				+ flash_extension + ", movie_extension=" + movie_extension + ", formmail_is_member="
				+ formmail_is_member + ", page_rows=" + page_rows + ", mobile_page_rows=" + mobile_page_rows
				+ ", visit=" + visit + ", max_po_id=" + max_po_id + ", stipulation=" + stipulation + ", privacy="
				+ privacy + ", open_modify=" + open_modify + ", memo_send_point=" + memo_send_point
				+ ", mobile_new_skin=" + mobile_new_skin + ", mobile_search_skin=" + mobile_search_skin
				+ ", mobile_connect_skin=" + mobile_connect_skin + ", mobile_faq_skin=" + mobile_faq_skin
				+ ", mobile_member_skin=" + mobile_member_skin + ", captcha_mp3=" + captcha_mp3 + ", editor=" + editor
				+ ", cert_use=" + cert_use + ", cert_ipin=" + cert_ipin + ", cert_hp=" + cert_hp + ", cert_kcb_cd="
				+ cert_kcb_cd + ", cert_kcp_cd=" + cert_kcp_cd + ", lg_mid=" + lg_mid + ", lg_mert_key=" + lg_mert_key
				+ ", cert_limit=" + cert_limit + ", cert_req=" + cert_req + ", sms_use=" + sms_use + ", sms_type="
				+ sms_type + ", icode_id=" + icode_id + ", icode_pw=" + icode_pw + ", icode_server_ip="
				+ icode_server_ip + ", icode_server_port=" + icode_server_port + ", googl_shorturl_apikey="
				+ googl_shorturl_apikey + ", facebook_appid=" + facebook_appid + ", facebook_secret=" + facebook_secret
				+ ", twitter_key=" + twitter_key + ", twitter_secret=" + twitter_secret + ", kakao_js_apikey="
				+ kakao_js_apikey + ", tmp1_subj=" + tmp1_subj + ", tmp2_subj=" + tmp2_subj + ", tmp3_subj=" + tmp3_subj
				+ ", tmp4_subj=" + tmp4_subj + ", tmp5_subj=" + tmp5_subj + ", tmp6_subj=" + tmp6_subj + ", tmp7_subj="
				+ tmp7_subj + ", tmp8_subj=" + tmp8_subj + ", tmp9_subj=" + tmp9_subj + ", tmp10_subj=" + tmp10_subj
				+ ", tmp1=" + tmp1 + ", tmp2=" + tmp2 + ", tmp3=" + tmp3 + ", tmp4=" + tmp4 + ", tmp5=" + tmp5
				+ ", tmp6=" + tmp6 + ", tmp7=" + tmp7 + ", tmp8=" + tmp8 + ", tmp9=" + tmp9 + ", tmp10=" + tmp10 + "]";
	}
}
