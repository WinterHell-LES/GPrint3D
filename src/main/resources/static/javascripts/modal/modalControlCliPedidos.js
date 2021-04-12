$('#trocarPedidoModal').on('show.bs.modal', function (e) 
{
    var button = e.relatedTarget;
    var modal = $(this);
    
    var pdcId = button.getAttribute('data-bs-pdcId');
    var ppdId = button.getAttribute('data-bs-ppdId');
    var prdNome = button.getAttribute('data-bs-prdNome');
    var prdQuantidade = button.getAttribute('data-bs-prdQuantidade');
    var cliId = button.getAttribute('data-bs-cliId');

    modal.find('.modal-body').find('#pdcId').val(pdcId);
    modal.find('.modal-body').find('#ppdId').val(ppdId);
    modal.find('.modal-body').find('#cliId').val(cliId);
    modal.find('.modal-body').find('#prdNome').text(prdNome);
    modal.find('.modal-body').find('#prdQuantidade').text(prdQuantidade);
    modal.find('.modal-body').find('#iQuantidade').attr({'max': prdQuantidade, 'min': 1}); 
});