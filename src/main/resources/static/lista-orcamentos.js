document.addEventListener('DOMContentLoaded', function() {
    // URL da sua API para buscar todos os orçamentos
    const apiUrl = 'http://localhost:8080/orcamento';

    // Pega a referência do corpo da tabela no HTML
    const tabelaCorpo = document.getElementById('tabela-corpo');

    // Faz a requisição GET para a API
    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na rede: ' + response.statusText);
            }
            return response.json(); // Converte a resposta para JSON
        })
        .then(data => {
            // Limpa qualquer conteúdo pré-existente (ex: mensagem de "carregando")
            tabelaCorpo.innerHTML = '';

            // Se não houver orçamentos, mostra uma mensagem
            if (data.length === 0) {
                const tr = document.createElement('tr');
                const td = document.createElement('td');
                td.colSpan = 6; // Ocupa todas as colunas
                td.textContent = 'Nenhum orçamento cadastrado.';
                td.style.textAlign = 'center';
                tr.appendChild(td);
                tabelaCorpo.appendChild(tr);
                return;
            }

            // Para cada orçamento na lista de dados, cria uma linha na tabela
            data.forEach(orcamento => {
                const tr = document.createElement('tr');

                // Cria as células da tabela (td) com os dados do orçamento
                    tr.innerHTML = `
                                    <td>${orcamento.id}</td>
                                    <td>${orcamento.produto ? orcamento.produto.id : 'N/A'}</td> <td>${orcamento.idFornecedor}</td>
                                    <td>R$ ${orcamento.precoCompra.toFixed(2).replace('.', ',')}</td>
                                    <td>${orcamento.quantidade}</td>
                                    <td><span class="status status-${orcamento.status.toLowerCase()}">${orcamento.status}</span></td>
                                `;
                
                // Adiciona a linha completa ao corpo da tabela
                tabelaCorpo.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Erro ao buscar orçamentos:', error);
            tabelaCorpo.innerHTML = `<tr><td colspan="6" style="color: red; text-align: center;">Falha ao carregar os orçamentos. Verifique se a API está no ar.</td></tr>`;
        });
});