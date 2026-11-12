const menuBtn = document.getElementById('menuBtn');
const sidebar = document.getElementById('sidebar');
const overlay = document.getElementById('overlay');

function toggleSidebar() {
sidebar.classList.toggle('active');
overlay.classList.toggle('active');
}

menuBtn.addEventListener('click', toggleSidebar);
overlay.addEventListener('click', toggleSidebar);

const searchInput = document.querySelector('input[name="search"]');
const searchForm = document.querySelector('.search-form');

const produtos = document.querySelectorAll('.produto-card');

if (searchInput) searchInput.addEventListener('input', function() {

const termoBusca = searchInput.value.toLowerCase();


produtos.forEach(produto => {

    const tituloProduto = produto.querySelector('h3').innerText.toLowerCase();

    
    if (tituloProduto.includes(termoBusca)) {
        produto.style.display = 'block'; 
    } else {
        produto.style.display = 'none'; 
    }
});
});

if (searchForm) searchForm.addEventListener('submit', function(event) {
event.preventDefault();
});

// ===== Barra de anúncios: troca a mensagem a cada 4 segundos =====
(function () {
    const mensagens = document.querySelectorAll('.barra-anuncios .anuncio');
    const barra = document.querySelector('.barra-anuncios');
    if (!barra || mensagens.length < 2) return;

    let atual = 0;
    let timer = null;

    mensagens.forEach(function (m, i) {
        m.setAttribute('aria-hidden', i === 0 ? 'false' : 'true');
    });

    function proxima() {
        mensagens[atual].classList.remove('ativo');
        mensagens[atual].setAttribute('aria-hidden', 'true');
        atual = (atual + 1) % mensagens.length;
        mensagens[atual].classList.add('ativo');
        mensagens[atual].setAttribute('aria-hidden', 'false');
    }

    function iniciar() { timer = setInterval(proxima, 4000); }
    function pausar() { clearInterval(timer); }

    barra.addEventListener('mouseenter', pausar);
    barra.addEventListener('mouseleave', iniciar);
    iniciar();
})();
