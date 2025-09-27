 const ORCAMENTO_URL = "http://localhost:8080/orcamento";

 document.getElementById('orcamentoForm').addEventListener('submit', function(event) {
             event.preventDefault(); // Impede o envio padrão do formulário

             const form = event.target;
             const messageDiv = document.getElementById('message');
             messageDiv.textContent = ''; // Limpa mensagens anteriores
             messageDiv.className = ''; // Limpa classes de estilo anteriores

             // Coleta os dados do formulário
             const produtoId = document.getElementById('produto').value;
             const precoCompra = parseFloat(document.getElementById('precoCompra').value);
             const quantidade = parseInt(document.getElementById('quantidade').value, 10);
             const dataValidade = document.getElementById('dataValidade').value;
             const dataEntrega = document.getElementById('dataEntrega').value;
             const idFornecedor = parseInt(document.getElementById('idFornecedor').value, 10);
             const idUnmedi = parseInt(document.getElementById('idUnmedi').value, 10);
             const garantia = document.getElementById('garantia').value;
             const condicoesPagamento = document.getElementById('condicoesPagamento').value;
             const idGrupoAprovador = parseInt(document.getElementById('idGrupoAprovador').value, 10);
             const idUserAprove = parseInt(document.getElementById('idUserAprove').value, 10);


             // Cria o objeto JavaScript no formato esperado pela API

             //evitar mexer da 27 à 43
             const orcamentoData = {
                 produto:
                 {
                    id: produtoId},
                 precoCompra: precoCompra,
                 quantidade: quantidade,
                 dataValidade: dataValidade,
                 dataEntrega: dataEntrega,
                 idFornecedor: idFornecedor,
                 idUnmedi: idUnmedi,
                 garantia: garantia,
                 condicoesPagamento: condicoesPagamento,
                 idGrupoAprovador: idGrupoAprovador, // Adicionado
                 idUserAprove: idUserAprove // Adicionado
                 // dataEmissao, dataGeracao e status são definidos pelo Service no backend
             };


             console.log("Enviando para a API:", JSON.stringify(orcamentoData, null, 2));
             // URL da sua API (a rota @RequestMapping e @PostMapping)
             const apiUrl = 'http://localhost:8080/orcamento';

             fetch(apiUrl, {
                 method: 'POST', // Método HTTP POST
                 headers: {
                     'Content-Type': 'application/json' // Informa ao servidor que o corpo é JSON
                 },
                 body: JSON.stringify(orcamentoData) // Converte o objeto JS para uma string JSON
             })
             .then(response => {
                 if (!response.ok) { // Se a resposta não for um sucesso (ex: 400, 500)
                     return response.json().then(errorData => Promise.reject(errorData));
                 }
                 return response.json(); // Se for sucesso (2xx), parseia o JSON da resposta
             })
             .then(data => {
                 messageDiv.textContent = `Orçamento ${data.id} salvo com sucesso! Status: ${data.status}`;
                 messageDiv.className = 'success-message';
                 form.reset(); // Limpa o formulário após sucesso
             })
             .catch(error => {
                 console.error('Erro ao salvar orçamento:', error);
                 let errorMessage = 'Ocorreu um erro desconhecido.';
                 if (error && error.message) {
                     errorMessage = error.message;
                 } else if (typeof error === 'object') {
                     errorMessage = error.error || error.message || JSON.stringify(error);
                 }
                 messageDiv.textContent = `Erro ao salvar orçamento: ${errorMessage}`;
                 messageDiv.className = 'error-message';
             });

                     });