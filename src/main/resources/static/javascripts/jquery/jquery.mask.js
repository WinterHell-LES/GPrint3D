$(function($) 
{
    $('.date').mask('00/00/0000');
    $('.time').mask('00:00:00');
    $('.date_time').mask('00/00/0000 00:00:00');
    $('.cep').mask('00000-000');
    $('.phone').mask('0000-0000');
    $('.phone_with_ddd').mask('(00) 0000-0000');
    $('.ddd').mask('(00)');
    $('.phone_us').mask('(000) 000-00000');
    $('.cartao').mask('0000 0000 0000 0000');
    $('.validade').mask('00/00');
    $('.cvv').mask('000');
    $('.numero').mask('0#');

    $('.cep_with_callback').mask('00000-000', {onComplete: function(cep) {
        console.log('Mask is done!:', cep);
      },
       onKeyPress: function(cep, event, currentField, options){
        console.log('An key was pressed!:', cep, ' event: ', event, 'currentField: ', currentField.attr('class'), ' options: ', options);
      }
    });

    $('.crazy_cep').mask('00000-000', {onKeyPress: function(cep){
      var masks = ['00000-000', '0-00-00-00'];
        mask = (cep.length>7) ? masks[1] : masks[0];
      $('.crazy_cep').mask(mask, this);
    }});

    $('.money').mask('000.000.000.000.000,00', {reverse: true});

    var Cpf_CnpjMask = function (val)
    {
        return val.replace(/\D/g, '').length >= 12 ? '00.000.000/0000-00' : '000.000.000-009';
    }

    $(".cpf-cnpj").mask(Cpf_CnpjMask, { onKeyPress: function(cpf_cnpj, e, currentField, options)
    {
      $(currentField).mask(Cpf_CnpjMask(cpf_cnpj), options);
    }});

    var CelphoneMask = function (val) 
    {
        return val.replace(/\D/g, '').length === 9 ? '00000-0000' : '0000-00009';
    };

    $(".celphone").mask(CelphoneMask, { onKeyPress: function(phone, e, currentField, options){
      $(currentField).mask(CelphoneMask(phone), options);
    }});
  });