$(window).on("load",function(){
    $(".loader-wrapper").fadeOut("slow");
});

$(window).resize(function(e){
    if($(window).width() < 457) {
    console.log($(window).width());
     $("#brand-image").each(function() {
       $(this).attr("src", "/images/logo-menu-min-inverted.png");
                 });
             } else if ($(window).width() >= 457) {
                 $("#brand-image").each(function() {
                 $(this).attr("src","/images/logo-menu-inverted.png");
                 });                        
     }         
 });

 window.onload = function() {
    if($(window).width() < 457) {
        console.log($(window).width());
         $("#brand-image").each(function() {
           $(this).attr("src", "/images/logo-menu-min-inverted.png");
                     });
                 } else if ($(window).width() >= 457) {
                     $("#brand-image").each(function() {
                     $(this).attr("src","/images/logo-menu-inverted.png");
                     });                        
     }    
  };