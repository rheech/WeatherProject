<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <script src="//cdn.ckeditor.com/4.8.0/standard/ckeditor.js"></script>
    <script src="/resources/static/js/jquery-3.2.1.min.js"></script>
    <link href="/resources/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="/resources/static/js/bootstrap.js"></script>
    <title>Home</title>
    <script>
        $(document).ready(function () {
            $('#home').click(function () {
                $('#notice').removeClass("active");
                $(this).addClass("active");
                $('#myBoard').hide();
                $('.jumbotron').slideDown();
            });

            $('#notice').click(function () {
                $('#home').removeClass("active");
                $(this).addClass("active");
                $('.jumbotron').slideUp(function () {
                    $('#myBoard').show();
                });
            });

            $('#login-btn').click(function() {
                $.ajax({
                    type: "POST",
                    url: "/loginProcessing",
                    data: {	"username" : $('#login-id').val(),
                        "password" : $('#login-pw').val()},
                    success: function() {
                        alert('로그인 성공');
                        location.reload();
                    }, error: function() {
                        alert('로그인 정보가 올바르지 않습니다.');
                    }
                });
            });

            $('#create').click(function() {
                CKupdate();
                $.ajax({
                    type: "POST",
                    url: "/createBoard",
                    data: {	"title" : $('#title').val(),
                        "text" : $('#editor').val(),
                        "id" : $('#adminid').val()},
                    success: function() {
                        alert('게시글 등록 성공');
                        location.reload();
                    }, error: function() {
                        alert('게시글 등록 실패');
                    }
                });
            });

        });
        function CKupdate(){
            for ( instance in CKEDITOR.instances )
                CKEDITOR.instances[instance].updateElement();
        }
    </script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-exl-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">날씨정보</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active" id="home"><a>Home</a></li>
                    <li id="notice"><a>공지사항</a></li>
                </ul>
                <div class="navbar-form navbar-right">
                    <c:choose>
                        <c:when test="${id != null}">
                            <button class="btn btn-success" onclick="location.href='/Logout'">${id} : 로그아웃</button>
                            <button class="btn btn-success" data-toggle="modal" data-target="#board-modal">공지사항</button>
                        </c:when>
                        <c:when test="${empty id}">
                            <button class="btn btn-success" data-toggle="modal" data-target="#login-modal">관리자로그인</button>
                        </c:when>
                    </c:choose>
                </div>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
    <div class="jumbotron">
        <div class="center-block" style="width: 380px"><h2>
            실시간 날씨 정보 사이트!
        </h2></div>
        <br/>
        <table class="table table-bordered">
            <tr>
                <th>측정날짜</th>
                <th>측정시간</th>
                <th>위치</th>
                <th>기온</th>
                <th>날씨</th>
                <th>낙뢰</th>
                <th>강수형태</th>
                <th>습도</th>
                <th>풍향</th>
                <th>풍속</th>
            </tr>
            <c:forEach items="${weatherDtoList}" var="list">
                <tr>
                    <td>${list.baseDate}</td>
                    <td>${list.baseTime}</td>
                    <td>${list.LOCATION}</td>
                    <td><c:choose>
                        <c:when test="${list.t1H >= 27}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/27.png" height="20" width="50" alt="나시티, 반바지, 민소매, 원피스" title="나시티, 반바지, 민소매, 원피스">
                        </c:when>
                        <c:when test="${list.t1H < 27 && list.t1H >= 23}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/23.png" height="20" width="50" alt="반팔, 얇은 셔츠, 얇은 긴팔, 반바지, 면바지" title="반팔, 얇은 셔츠, 얇은 긴팔, 반바지, 면바지">
                        </c:when>
                        <c:when test="${list.t1H < 23 && list.t1H >= 20}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/20.png" height="20" width="50" alt="긴판티, 가디건, 후드티, 면바지, 슬랙스, 스키니" title="긴판티, 가디건, 후드티, 면바지, 슬랙스, 스키니">
                        </c:when>
                        <c:when test="${list.t1H < 20 && list.t1H >= 17}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/17.png" height="20" width="50" alt="니트, 가디건, 후드티, 맨투맨, 청바지, 면바지, 슬랙스, 원피스" title="니트, 가디건, 후드티, 맨투맨, 청바지, 면바지, 슬랙스, 원피스">
                        </c:when>
                        <c:when test="${list.t1H < 17 && list.t1H >= 12}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/12.png" height="20" width="50" alt="자켓, 셔츠, 가디건, 간절기 야상, 살색스타킹" title="자켓, 셔츠, 가디건, 간절기 야상, 살색스타킹">
                        </c:when>
                        <c:when test="${list.t1H < 12 && list.t1H >= 9}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/10.png" height="20" width="50" alt="트렌치코트, 간절기 야상, 여러겹 껴입기" title="트렌치코트, 간절기 야상, 여러겹 껴입기">
                        </c:when>
                        <c:when test="${list.t1H < 9 && list.t1H >= 5}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/5.png" height="20" width="50" alt="코트, 가죽자켓" title="코트, 가죽자켓">
                        </c:when>
                        <c:when test="${list.t1H < 5}">
                            ${list.t1H} ℃ <img src="/resources/static/img/T1H/5low.png" height="20" width="50" alt="겨울 옷 (야상, 패딩, 목도리 등등 다)" title="겨울 옷 (야상, 패딩, 목도리 등등 다)">
                        </c:when>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${list.SKY == 1}">
                            맑음 <img src="/resources/static/img/SKY/1.gif" height="20" width="20">
                        </c:when>
                        <c:when test="${list.SKY == 2}">
                            구름조금 <img src="/resources/static/img/SKY/2.png" height="20" width="20">
                        </c:when>
                        <c:when test="${list.SKY == 3}">
                            구름많음 <img src="/resources/static/img/SKY/3.png" height="20" width="20">
                        </c:when>
                        <c:when test="${list.SKY == 4}">
                            흐림 <img src="/resources/static/img/SKY/4.png" height="20" width="20">
                        </c:when>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${list.LGT == 0}">
                            없음
                        </c:when>
                        <c:when test="${list.LGT == 1}">
                            있음
                        </c:when>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${list.PTY == 0}">
                            없음 <img src="/resources/static/img/PTY/0.png" height="20" width="20">
                        </c:when>
                        <c:when test="${list.PTY == 1}">
                            비 <img src="/resources/static/img/PTY/1.png" height="20" width="20">
                        </c:when>
                        <c:when test="${list.PTY == 2}">
                            진눈개비 <img src="/resources/static/img/PTY/2.ico" height="20" width="20">
                        </c:when>
                        <c:when test="${list.PTY == 3}">
                            눈 <img src="/resources/static/img/PTY/3.png" height="20" width="20">
                        </c:when>
                    </c:choose></td>
                    <td>${list.REH}</td>
                    <td><c:choose>
                        <c:when test="${list.VEC < 45 && list.VEC >= 0}">
                            N-NE (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 90 && list.VEC >= 45}">
                            NE-E (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 135 && list.VEC >= 90}">
                            E-SE (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 180 && list.VEC >= 135}">
                            SE-S (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 225 && list.VEC >= 180}">
                            S-SW (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 270 && list.VEC >= 225}">
                            SW-W (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 315 && list.VEC >= 270}">
                            W-NW (${list.VEC})
                        </c:when>
                        <c:when test="${list.VEC < 360 && list.VEC >= 315}">
                            NW (${list.VEC})
                        </c:when>
                    </c:choose></td>
                    <td>${list.WSD}m/s</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="row" id="myBoard" hidden="hidden">
        <div class="center-block" style="width: 150px"><h2>
            공지사항
        </h2></div>
        <hr style="border: solid 2px">
        <c:forEach items="${boardList}" var="board">
            <h2>제목 : ${board.title}</h2><br>
            <h4 class="right">작성자 : ${board.adminname}  ||  작성날짜 : ${board.regdate}</h4>
            <hr style="border: solid 2px">
            <h3>내용</h3><br/>
            <h3>${board.content}</h3>
        </c:forEach>
        <hr style="border: solid 2px">
    </div>
</div>
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="login-modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h1>로그인</h1><br>
                <label for="login-id">아이디</label>
                <input class="form-control" type="text" id="login-id" name="username" placeholder="아이디" required="required">
                <br/>
                <label for="login-pw">암호</label>
                <input class="form-control" type="password" id="login-pw" name="password" placeholder="비밀번호" required="required">
                <br/>
                <button type="button" id="login-btn" name="login" class="btn btn-default">로그인</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="board-modal" tabindex="-1" role="dialog" aria-labelledby="board-modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h1>글쓰기</h1><br>
                <label for="login-id">제목</label>
                <input class="form-control" type="text" id="title" name="title" placeholder="제목" required="required">
                <br/>
                <label for="login-pw">내용</label>
                <textarea class="form-control" name="editor" id="editor" rows="15" cols="80"></textarea>
                <script> CKEDITOR.replace( 'editor' );</script>
                <br/>
                <input type="hidden" id="adminid" value="${id}" >
                <button type="button" id="create" name="create" class="btn btn-default">제출</button>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>
