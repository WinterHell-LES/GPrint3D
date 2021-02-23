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

function desabilitarEndereco() {
    var checkBox = document.getElementById('enderecoPadrao');
    var desabilitarDiv = document.getElementById('desabilitar');
    
    checkBox.checked ? desabilitarDiv.style.display = 'none' : desabilitarDiv.style.display = 'flex';
}