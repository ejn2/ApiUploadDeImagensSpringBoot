<h1>Upload de imagens</h1>

<p>&nbsp&nbsp&nbspNesse pequeno projeto, mostra como fazer upload de arquivos (nesse caso, apenas de imagens), com o tratamento dos principais erros que poderiam ocorrer em um ambiente de produção.</p>

<h2>#Rotas</h2>

<li><h4>Lista todas as imagens</h4></li>

<p>GET - /api/v1/galeria</p>

<li><h4>Encontra uma imagem específica (é necessário passar o id)</h4></li>

<p>GET - /api/v1/galeria/1</p>

<li><h4>Registra uma nova imagem</h4></li>

<p>POST - /api/v1/galeria</p>

<li><h4>Exclui uma imagem específica (é necessário passar o id)</h4></li>

<p>DELETE- /api/v1/galeria/1</p>


<h3>O método PUT não foi implementado, só estará disponível na versão 2 deste mesmo projeto.</h3>
