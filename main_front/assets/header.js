function acordeaoMenu(id) {
    let item = document.getElementById(id);

    if (item.className == "fechado"){
        item.className = "aberto";
    } else {
        item.className = "fechado";
    }
}