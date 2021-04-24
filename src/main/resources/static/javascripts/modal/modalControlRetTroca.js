$('#retornoProdutoModal').on('show.bs.modal', function (e) 
{
    var button = e.relatedTarget;
    var modal = $(this);
    
    var pdtId = button.getAttribute('data-bs-pdtId');
    var pdtNome = button.getAttribute('data-bs-pdtNome');
    var pdtQuantidade = button.getAttribute('data-bs-pdtQuantidade');

    modal.find('.modal-body').find('#pdtId').val(pdtId);
    modal.find('.modal-body').find('#pdtNome').text(pdtNome);
    modal.find('.modal-body').find('#pdtQuantidade').text(pdtQuantidade);
    modal.find('.modal-body').find('#iQuantidade').attr({'max': pdtQuantidade, 'min': 0}); 
});