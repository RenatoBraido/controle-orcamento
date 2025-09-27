const ORCAMENTO_URL = "http://localhost:8080/orcamento";
const PRODUTOS_API_URL = 'http://localhost:8080/orcamento/ListarProdutos'; // URL para listar produtos

document.addEventListener('DOMContentLoaded', function() {
    const orcamentoForm = document.getElementById('orcamentoForm');
    const produtoSelect = document.getElementById('produtoSelect');
    const messageDiv = document.getElementById('message');

    // Função para carregar os produtos na caixa suspensa
    async function loadProdutosIntoSelect() {
        try {
            const response = await fetch(PRODUTOS_API_URL);
            if (!response.ok) {
                throw new Error('Erro ao carregar produtos: ' + response.statusText);
            }
            const produtos = await response.json();

            // Limpa opções existentes, exceto o placeholder
            produtoSelect.innerHTML = '<option value="">Selecione um produto</option>';

            produtos.forEach(produto => {
                const option = document.createElement('option');
                option.value = produto.id; // O valor da opção será o ID do produto
                option.textContent = produto.nome; // O texto visível será o nome do produto
                produtoSelect.appendChild(option);
            });

        } catch (error) {
            console.error('Falha ao carregar a lista de produtos:', error);
            // Opcional: Desabilitar o select e mostrar uma mensagem de erro
            produtoSelect.innerHTML = '<option value="">Erro ao carregar produtos</option>';
            produtoSelect.disabled = true;
            messageDiv.textContent = 'Erro ao carregar a lista de produtos. Verifique a API.';
            messageDiv.className = 'error-message';
        }
    }

    // Carrega os produtos quando a página é carregada
    loadProdutosIntoSelect();

    // Listener para o envio do formulário
    orcamentoForm.addEventListener('submit', async function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        messageDiv.textContent = ''; // Limpa mensagens anteriores
        messageDiv.className = ''; // Limpa classes de estilo anteriores

        // Coleta os dados do formulário
        const selectedProdutoId = produtoSelect.value; // Pega o ID do produto selecionado
        const precoCompra = parseFloat(document.getElementById('precoCompra').value);
        const quantidade = parseInt(document.getElementById('quantidade').value, 10);
        const dataValidade = document.getElementById('dataValidade').value;
        const dataEntrega = document.getElementById('dataEntrega').value;
        const idFornecedor = parseInt(document.getElementById('idFornecedor').value, 10); // Manteremos como input por enquanto
        const idUnmedi = parseInt(document.getElementById('idUnmedi').value, 10);
        const garantia = document.getElementById('garantia').value;
        const condicoesPagamento = document.getElementById('condicoesPagamento').value;
        const idGrupoAprovador = parseInt(document.getElementById('idGrupoAprovador').value, 10);
        const idUserAprove = parseInt(document.getElementById('idUserAprove').value, 10);

        // Validação simples para garantir que um produto foi selecionado
        if (!selectedProdutoId) {
            messageDiv.textContent = 'Por favor, selecione um produto.';
            messageDiv.className = 'error-message';
            return;
        }

        // Cria o objeto JavaScript no formato esperado pela API
        const orcamentoData = {
            produto: {
               id: selectedProdutoId // Usa o ID do produto selecionado
            },
            precoCompra: precoCompra,
            quantidade: quantidade,
            dataValidade: dataValidade,
            dataEntrega: dataEntrega,
            idFornecedor: idFornecedor,
            idUnmedi: idUnmedi,
            garantia: garantia,
            condicoesPagamento: condicoesPagicoesPagamento,
            idGrupoAprovador: idGrupoAprovador,
            idUserAprove: idUserAprove
        };

        console.log("Enviando para a API:", JSON.stringify(orcamentoData, null, 2));

        try {
            const response = await fetch(ORCAMENTO_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(orcamentoData)
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Erro ao salvar orçamento.');
            }

            const data = await response.json();
            messageDiv.textContent = `Orçamento ${data.id} salvo com sucesso! Status: ${data.status}`;
            messageDiv.className = 'success-message';
            orcamentoForm.reset(); // Limpa o formulário após sucesso
            produtoSelect.value = ""; // Reseta o select para o placeholder
        } catch (error) {
            console.error('Erro ao salvar orçamento:', error);
            messageDiv.textContent = `Erro ao salvar orçamento: ${error.message || 'Ocorreu um erro desconhecido.'}`;
            messageDiv.className = 'error-message';
        }
    });
});