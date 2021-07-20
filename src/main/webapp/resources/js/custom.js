var tbodies = document.getElementsByClassName("ui-datatable-scrollable-body");
for(mitem of tbodies){
    mitem.removeAttribute("tabindex");
}