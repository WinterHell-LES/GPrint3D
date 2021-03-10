function hideElem(id, self)
{
    let elem = document.getElementById(id)

    elem.classList.add("visually-hidden");
    self.classList.remove("bi-caret-up");
    self.classList.add("bi-caret-down");
    self.setAttribute("onclick", "showElem('" + id + "', this)");
}

function showElem(id, self)
{
    let elem = document.getElementById(id)

    elem.classList.remove("visually-hidden");
    self.classList.remove("bi-caret-down");
    self.classList.add("bi-caret-up");
    self.setAttribute("onclick", "hideElem('" + id + "', this)");
}