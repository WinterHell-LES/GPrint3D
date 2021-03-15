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

function hideElement(selfElement)
{
    //let selfElement = document.getElementsByClassName("pedidos")[0];
    let siblingSonElement = selfElement.nextElementSibling.firstElementChild;
    let iconReference = findCaret(selfElement, "up");

    siblingSonElement.classList.add("visually-hidden");
    iconReference.classList.remove("bi-caret-up");
    iconReference.classList.add("bi-caret-down");
    selfElement.setAttribute("onclick", "showElement(this)");
}
function showElement(selfElement)
{
    let siblingSonElement = selfElement.nextElementSibling.firstElementChild;
    let iconReference = findCaret(selfElement, "down");

    siblingSonElement.classList.remove("visually-hidden");
    iconReference.classList.remove("bi-caret-down");
    iconReference.classList.add("bi-caret-up");
    selfElement.setAttribute("onclick", "hideElement(this)");
}

function findCaret(parent, position)
{
    let iconsReference = parent.getElementsByTagName("i");
    let iconRefence;

    Array.prototype.forEach.call(iconsReference, child => {
        if (child.classList.contains("bi-caret-" + position))
            return iconRefence = child;
      });

      return iconRefence;
}