function mascaraValCard(elem){
    v = elem.value

    v=v.replace(/\D/g,"");            
    v=v.replace(/^(d{2})(d{2})/g,"$1/$2"); 

    return elem.value = v;
}