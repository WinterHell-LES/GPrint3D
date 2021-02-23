$(document).ready(function() 
{
    var table = $('#example').DataTable(
    {
        paging:   true,
        ordering: true,
        info:     true,

        columnDefs: [
        { 
            targets: [2, 3, 4, 5],
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