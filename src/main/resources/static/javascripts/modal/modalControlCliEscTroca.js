$('#escolherTrocaModal').on('show.bs.modal', function (e) 
{
    var button = e.relatedTarget;
    var modal = $(this);
    
    var pdtId = button.getAttribute('data-bs-pdtId');

    modal.find('.modal-body').find('#pdtId').val(pdtId);
});