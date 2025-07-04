<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <h1>Video Voting Competition</h1>
<div class="right-controls">
  
<a href="${pageContext.request.contextPath}/ranking?page=1" class="ranking-btn">Rankings</a>

  <span class="header-icon">
    <button id="refresh-btn" class="icon-button" type="button">
      <img src="${pageContext.request.contextPath}/assets/images/refresh.svg" alt="Refresh"/>
    </button>
  </span>

  <span >
   <div class="share-wrapper">
     <span class="header-icon">
  <img id="share-btn"
       src="${pageContext.request.contextPath}/assets/images/share.svg"
       alt="Share"
       style="cursor: pointer;" />
     </span>

  <ul id="share-menu" class="popup-menu">
    <li onclick="navigator.clipboard.writeText(window.location.href)
                  .then(()=>alert('Link copied!'))
                  .catch(()=>alert('Copy failed'))">
      Copy link
    </li>
    <li onclick="window.open('https://twitter.com/intent/tweet?url='+encodeURIComponent(window.location.href))">
      Share on Twitter
    </li>
    <li onclick="window.open('https://www.facebook.com/sharer/sharer.php?u='+encodeURIComponent(window.location.href))">
      Share on Facebook
    </li>
  </ul>
</div>  </span>

  <ul id="share-menu" class="popup-menu">
    <li
</div>

</header> 

