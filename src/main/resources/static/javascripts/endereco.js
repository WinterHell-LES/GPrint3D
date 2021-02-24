function duplicarCampos(){
    var clone = document.getElementById('origem').cloneNode(true);
    var destino = document.getElementById('destino');
    destino.appendChild (clone);

    var camposClonados = clone.getElementsByTagName('input');

    for(i=0; i<camposClonados.length;i++){
        camposClonados[i].value = '';
    }
}

function removerCampos(id){
    var node1 = document.getElementById('destino');
    node1.removeChild(node1.childNodes[0]);
}

function desabilitarEndereco(id) {
    var checkBox = document.getElementById('enderecoPadrao');
    var desabilitarDiv = document.getElementById('desabilitar');
    var camposInput = desabilitarDiv.getElementsByTagName('input');
    var camposTextArea = desabilitarDiv.getElementsByTagName('textarea');
    var camposSelect = desabilitarDiv.getElementsByTagName('select');
    
    if (checkBox.checked) {
        desabilitarDiv.style.display = 'none';
        
        for(i=0; i<camposInput.length;i++){
            camposInput[i].value = '';
        }
    
        for(i=0; i<camposTextArea.length;i++){
            camposTextArea[i].value = '';
        }
    
        for(i=0; i<camposSelect.length;i++){
            camposSelect[i].selectedIndex = 0;
        }
     }
     
     else {
     desabilitarDiv.style.display = 'flex';
     }
    
    

}