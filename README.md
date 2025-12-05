# Explora Gostoso

<p align="center">
  <strong>Sistema de Agência de Turismo para São Miguel do Gostoso/RN</strong>
</p>

Sistema web desenvolvido para conectar turistas às melhores experiências da região. A plataforma funciona como uma vitrine interativa de roteiros para os usuários e oferece um painel administrativo completo, onde gestores podem criar e gerenciar novos pacotes turísticos.

## Sobre o Projeto

O **Explora Gostoso** é uma aplicação web full-stack desenvolvida para gerenciar a venda de pacotes turísticos. O sistema permite que administradores cadastrem roteiros e que clientes montem seus carrinhos de compras de forma dinâmica.

O diferencial do projeto é o fluxo de checkout simplificado: ao finalizar o pedido, o sistema gera uma mensagem detalhada com os itens e o valor total, redirecionando o cliente diretamente para o **WhatsApp** da agência.

## Funcionalidades

- **Área Pública:**
  - Landing Page com destaques turísticos.
  - Listagem de pacotes com busca e filtros.
  - Cadastro e Login de clientes.
  
- **Carrinho de Compras Inteligente:**
  - Adição de itens via **AJAX** atualização instantânea sem recarregar a página.
  - Persistência de dados, o carrinho fica salvo no banco mesmo se o usuário sair.
  - Cálculo automático de subtotais e total.
  - Remoção de itens.

- **Checkout:**
  - Integração com API do WhatsApp para envio do pedido formatado.

- **Área Administrativa :**
  - Cadastro, edição e inativação de pacotes turísticos.
  - Controle de preços e imagens.
 
## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando a arquitetura **MVC**.

- **Back-end:** Java , Play Framework 1.5.3
- **Front-end:** HTML5, CSS3 (Customizado + Bootstrap 5), jQuery
- **Banco de Dados:** H2 Database (JPA/Hibernate)
- **Versionamento:** Git, GitHub
- **Ferramentas:** Eclipse IDE, Google Chrome e Edge 

## Layout Responsivo

O projeto foi desenvolvido com foco na experiência do usuário, para garantir que o layout se adapte perfeitamente a qualquer tamanho de tela, desde dispositivos móveis até desktops.

<div align="center">
  <img src="public/images/img2.jpg" alt="Demonstração de Responsividade em múltiplos dispositivos" width="80%">
</div>
<br>

---

