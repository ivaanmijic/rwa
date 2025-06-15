
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>${param.title} - Video Voting Competition</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Inter" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script>
        window.ctxPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/js/video-loader.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="page-wrapper">
    <jsp:include page="/WEB-INF/views/header.jsp"/>
    <div class="main">
    <div class="iframe-container" id="rand-videos-container">
    
    </div>
    <div class="rank-container">
        <h2>Top 5 Videos</h2>
        <div class="table-container">
            <div class="row header">
                <div class="cell image"></div>
                <div class="cell headline">Headline</div>
                <div class="cell votes">Votes</div>
                <div class="cell rank">Rank</div>
            </div>

            <div class="row">
                <div class="cell image">
                    <img src="https://img.youtube.com/vi/e-P5IFTqB98/hqdefault.jpg" alt="Black Holes Explained"/>
                </div>
                <div class="cell headline">Black Holes Explained – From Birth to Death</div>
                <div class="cell votes">24/25</div>
                <div class="cell rank">1st</div>
            </div>

            <div class="row">
                <div class="cell image">
                    <img src="https://img.youtube.com/vi/G4H1N_yXBiA/hqdefault.jpg" alt="Climate Change"/>
                </div>
                <div class="cell headline">Causes and Effects of Climate Change | National Geographic</div>
                <div class="cell votes">23/25</div>
                <div class="cell rank">2nd</div>
            </div>

            <div class="row">
                <div class="cell image">
                    <img src="https://img.youtube.com/vi/2pp17E4E-O8/hqdefault.jpg" alt="CRISPR"/>
                </div>
                <div class="cell headline">Genome Editing with CRISPR-Cas9</div>
                <div class="cell votes">27/30</div>
                <div class="cell rank">3rd</div>
            </div>

            <div class="row">
                <div class="cell image">
                    <img src="https://img.youtube.com/vi/vo4pMVb0R6M/hqdefault.jpg" alt="Psychology"/>
                </div>
                <div class="cell headline">Intro to Psychology: Crash Course Psychology #1</div>
                <div class="cell votes">17/20</div>
                <div class="cell rank">4th</div>
            </div>

            <div class="row">
                <div class="cell image">
                    <img src="https://img.youtube.com/vi/21eFwbb48sE/hqdefault.jpg" alt="Internet History"/>
                </div>
                <div class="cell headline">Who Invented the Internet? And Why?</div>
                <div class="cell votes">15/20</div>
                <div class="cell rank">5th</div>
            </div>
        </div>
    </div>
    </div>

    <jsp:include page="/WEB-INF/views/footer.jsp"/>
</div>
</body>
</html>
