$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget); // pega o evento disparado pelo botão do modal
	
	var codigoTitulo = button.data('codigo'); // pega o código pelo data-target
	var descricaoTitulo = button.data('descricao'); // pega a descrição pelo data-target
	
	var modal = $(this); // modal recebe  o modal passado
	var form = modal.find('form'); // modal encontra o form
	var action = form.data('url-base'); // modal pega a ação
	if (!action.endsWith('/')) {
		action += '/'; // concatena com /
	}
	form.attr('action', action + codigoTitulo); //pega a ação e passa o código
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
	//exibe o modal com o corpo contendo a descrição do título.
});


$(function (){
		$('[rel = "tooltip"]').tooltip();
		$('.js-currency').maskMoney({decimal:',',thousands:'.',allowZero:true});
	
});