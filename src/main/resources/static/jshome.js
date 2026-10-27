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

searchInput.addEventListener('input', function() {

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

searchForm.addEventListener('submit', function(event) {
event.preventDefault();
});