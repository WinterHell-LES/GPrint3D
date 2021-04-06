$(document).ready(function() 
{
    var numCollumn = $('#example thead th').last().index();

    var table = $('#example').DataTable(
    {
        paging:   true,
        ordering: true,
        info:     true,
        stateSave:true,

        columnDefs: [
        { 
            targets: numCollumn,
            orderable: false
        }],

        language: 
        {
            "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
        },

        initComplete: function () 
        {
            this.api().columns().every( function () 
            {
                var that = this;
 
                $( 'input', this.footer() ).on( 'keyup change clear', function () 
                {
                    if ( that.search() !== this.value ) 
                    {
                        that.search(this.value).draw();
                    }
                });
            });
        }
    });
});