/* 데이터 조회
   url : url
   type : method 타입
   data : 조회 데이터
   onSuccess : 성공 시 수행 메소드
   onParam : 성공 시 수행할 메소드에 전달할 파라미터
   paging : 초기화 여부
*/
var getList = function(url, type, data, onSuccess, onParam){
	$.ajax({
	      url:url
	    , async:true
	    , type:type
	    , data:data
	    , dataType:'json'
	    , success:function(e) {
	    	data = e;
	    	
	    	onSuccess(data, onParam);
	    	
	    	console.log(data);
	    }
	    , error:function(e) {
	    	console.log(e);
	    }
	});
}

/* 사용자 컴포넌트 생성
   email : 사용자 이메일
   ranking : 사용자 랭킹
*/
var getUserComp = function(email, ranking){
	var feather = "";
	var html = "";
	console.log("getUserComp : " + email + "\t" + ranking);
	switch(ranking){
	case 1 : feather = "chrome"; break;
	case 9 : feather = "compass"; break;
	case 20 : feather = "plus-circle"; break;
	default : feather = "circle"; break;
	}
	html = '<span class="mt-3 mr-2 border" data-feather="' + feather + '" id="rankIcon1"></span><span>';
	html += email + '</span>';
	
	return html;
}

/* 댓글 컴포넌트 생성
   data : 페이징 처리된 데이터
*/
var getCommentCompList = function(data){
	var feather = "";
	var html = "";
	var comment = null;
	for(var i = 0; i < data.numberOfElements; i++){
		comment = data.content[i];
		console.log(i);
		console.log(comment);
		html += '<div class="col-9 border-top">';
		html += getUserComp(comment.email, comment.ranking);
		html += '<p class="mt-3 text-justify" style="word-break:break-all;width:100%;">' + comment.commentContents + '</p>';
		html += '</div>';
		html += '<div class="col-3 border-top">';
		html += '<p class="text-right mt-3">';
		html += '<span>' + comment.regDate + '</span><br>';
		html += '<button type="button" id="buttonDelete" class="btn btn-danger btn-sm mt-3" data="' + comment.commentIdx + '">';
		html += '<span data-feather="trash"></span>';
		html += '</button>';
		html += '</p>';
		html += '</div>';
	}
	
	return html;
}