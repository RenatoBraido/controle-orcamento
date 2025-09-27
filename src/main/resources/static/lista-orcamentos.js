document.addEventListener('DOMContentLoaded', function() {
    // URLs das suas APIs
    const orcamentosApiUrl = 'http://localhost:8080/orcamento';
    const produtosApiUrl = 'http://localhost:8080/orcamento/ListarProdutos';
    const fornecedoresApiUrl = 'http://localhost:8080/orcamento/listarFornecedores'; // Sua nova rota

    const tabelaCorpo = document.getElementById('tabela-corpo');

    // Função para buscar dados da API
    async function fetchData(url) {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Erro na rede: ${response.statusText} (URL: ${url})`);
        }
        return response.json();
    }

    // Função principal para carregar os orçamentos, produtos e fornecedores
    async function carregarOrcamentosComDetalhes() {
        try {
            // 1. Busca os produtos e cria um mapa para fácil consulta
            const produtos = await fetchData(produtosApiUrl);
            const produtoMap = {};
            produtos.forEach(produto => {
                produtoMap[produto.id] = produto.nome; // Mapeia ID do produto para o Nome do produto
            });

            // 2. Busca os fornecedores e cria um mapa para fácil consulta
            const fornecedores = await fetchData(fornecedoresApiUrl);
            const fornecedorMap = {};
            fornecedores.forEach(fornecedor => {
                fornecedorMap[fornecedor.id] = fornecedor.nome; // Mapeia ID do fornecedor para o Nome do fornecedor
            });

            // 3. Busca os orçamentos
            const orcamentos = await fetchData(orcamentosApiUrl);

            // Limpa o corpo da tabela
            tabelaCorpo.innerHTML = '';

            // Se não houver orçamentos
            if (orcamentos.length === 0) {
                const tr = document.createElement('tr');
                const td = document.createElement('td');
                td.colSpan = 6; // Ajuste para o número correto de colunas se adicionar mais
                td.textContent = 'Nenhum orçamento cadastrado.';
                td.style.textAlign = 'center';
                tr.appendChild(td);
                tabelaCorpo.appendChild(tr);
                return;
            }

            // 4. Para cada orçamento, cria a linha da tabela usando os nomes de produto e fornecedor
            orcamentos.forEach(orcamento => {
                const tr = document.createElement('tr');

                // Obtém o nome do produto
                const produtoId = orcamento.produto ? orcamento.produto.id : null;
                const produtoNome = produtoId ? (produtoMap[produtoId] || 'Produto Desconhecido') : 'N/A';

                // Obtém o nome do fornecedor
                const fornecedorId = orcamento.idFornecedor; // Pelo seu HTML, parece ser 'idFornecedor' diretamente no orçamento
                const fornecedorNome = fornecedorId ? (fornecedorMap[fornecedorId] || 'Fornecedor Desconhecido') : 'N/A';

                tr.innerHTML = `
                    <td>${orcamento.id}</td>
                    <td>${produtoNome}</td>
                    <td>${fornecedorNome}</td> <td>R$ ${orcamento.precoCompra.toFixed(2).replace('.', ',')}</td>
                    <td>${orcamento.quantidade}</td>
                    <td><span class="status status-${orcamento.status.toLowerCase()}">${orcamento.status}</span></td>
                `;

                tabelaCorpo.appendChild(tr);
            });

        } catch (error) {
            console.error('Erro ao carregar orçamentos, produtos ou fornecedores:', error);
            tabelaCorpo.innerHTML = `<tr><td colspan="6" style="color: red; text-align: center;">Falha ao carregar os dados. Verifique as APIs e a configuração de CORS.</td></tr>`;
        }
    }

    // Chama a função para carregar tudo quando o DOM estiver pronto
    carregarOrcamentosComDetalhes();
});