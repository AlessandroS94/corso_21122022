// JavaScript Document
//Required <link rel="stylesheet" href="dist/css/adminlte.min.css">
//Required <link rel="stylesheet" href="dist/css/adminlte.min.css">
//Required <link rel="stylesheet" href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
//Required <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
//Required <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">


/**********************************************
 * Create element for Chat
 */

class ChatHTML5 {
    constructor(userAuth, path, outputRef, asset) {
        this.path = path;
        this.userAuth = userAuth;

        $.ajax({
            url: this.path,
            success: function (data) {
                this.cardFooter = new cardFooter(outputRef).get();
                this.section = document.getElementById("chatHTML5");
                this.cardTitle = new cardTitle("Chat").get();
                this.card = document.createElement("div");
                this.card.className = "card direct-chat direct-chat-primary";
                this.container = document.createElement("div");
                this.container.className = 'container-fluid';
                this.cardBody = new cardBody(data, userAuth, asset).get();
                this.card.appendChild(this.cardTitle);
                this.card.appendChild(this.cardBody);
                this.card.appendChild(this.cardFooter);
                this.container.appendChild(this.card);
                this.section.appendChild(this.container);

            },
            error: function (richiesta, stato, errori) {
                var check = document.getElementById("myModal");
                if (check)
                    document.getElementById("myModal").remove();
                var warning = new modalWarning();
                this.section = document.getElementById("chatHTML5");
                this.section.appendChild(warning.get());
                $("#myModal").modal('show');
            }
        });
        setInterval(updateCardBody, 30000, path, userAuth, asset);
    }
}

/**********************************************
 *
 * start title card with component
 */
class cardTitle {
    constructor(text) {
        this.title = document.createElement("h3");
        this.title.className = 'card-title';
        this.title.innerHTML = text;
        this.divContainer = document.createElement("div");
        this.divContainer.className = 'card-header';
        this.divContainer.appendChild(this.title);
    }

    get() {
        return this.divContainer;
    }
}

/**********************************************+
 *
 * start body card with component
 */
class cardBody {

    constructor(data, userId, asset) {
        this.cardBody = document.createElement("div");
        this.cardBody.className = "card-body";
        this.cardBody.setAttribute('id', 'messages');
        this.divContainer = new messages(data, userId, asset).get();
        this.cardBody.appendChild(this.divContainer);
    }

    get() {
        return this.cardBody;
    }
}

class messages {

    constructor(data, userId, asset) {
        this.divContainer = document.createElement("div");
        this.divContainer.className = 'direct-chat-messages';
        this.divContainer.style = 'height: 400px';
        let messages;
        messages = data.messages;
        let id = 0;
        messages.forEach(a => {
                    if (a.message != "START") {
                        let msg = new message(a, userId, asset,id);
                        this.divContainer.appendChild(msg.get());
                        id ++;
                    }
                }
            );
    }

    get() {
        return this.divContainer;
    }
}

/**********************************************+
 *
 * start footer card with component
 */
class cardFooter {

    constructor(url) {
        this.container = document.createElement("div");
        this.container.className = 'card-footer';
        this.form = document.createElement("form");
        this.form.setAttribute('id', "chat");
        this.form.setAttribute('name', 'chat');
        this.form.setAttribute('method', "post");
        this.form.setAttribute('enctype', "multipart/form-data")
        this.form.setAttribute('action', url);

        this.inputGroup = document.createElement("div");
        this.inputGroup.className = "input-group";
        this.input = document.createElement('input');
        this.input.className = "form-control";
        this.input.setAttribute('maxlength', 200);
        this.input.type = "text";
        this.input.setAttribute("id","messageChat");
        this.input.name = "message";
        this.button = document.createElement('button');
        this.button.className = "btn btn-primary";
        this.button.type = "button";
        this.button.setAttribute("onClick","send()");
        this.button.innerHTML = "Invia";

        this.inputGroup.appendChild(this.input);
        this.attach = new Attachment();
        this.inputGroup.appendChild(this.attach.get());
        this.inputGroup.appendChild(this.button);
        this.form.appendChild(this.inputGroup);
        this.container.appendChild(this.form);
    }

    get() {
        return this.container;
    }
}

/**********************************************+
 *
 * start footer card with component
 */
class Attachment {

    constructor() {
        this.attachment = document.createElement("div");
        this.attachment.className = "btn btn-default btn-file";
        this.icon = document.createElement("i");
        this.icon.className = "fas fa-paperclip";
        this.input = document.createElement("input");
        this.input.type = "file";
        this.input.name = "attachment";
        this.input.setAttribute("id", "attachment");
        this.attachment.appendChild(this.input);
        this.attachment.appendChild(this.icon);
    }

    get() {
        return this.attachment;
    }
}

/**********************************************+
 *
 * start message
 */
class message {

    constructor(msg, userId, asset,id) {
        this.container = document.createElement("div");
        if (msg.idUser == userId)
            this.container.className = "direct-chat-msg right";
        else
            this.container.className = "direct-chat-msg";
        this.info = document.createElement("div");
        this.info.className = "direct-chat-infos clearfix";
        this.span1 = document.createElement("span");
        this.span1.className = "direct-chat-name float-left";
        this.span1.innerHTML = "" +  msg.name;
        this.span2 = document.createElement("span");
        this.span2.className = "direct-chat-timestamp float-right";
        this.span2.innerHTML = msg.date;
        this.icon = document.createElement('i');
        this.icon.className = 'direct-chat-img fa fa-user fa-2x';
        this.text = document.createElement('div');
        this.text.className = "direct-chat-text";
        this.text.innerHTML = msg.message;
        if (msg.file) {
            this.href = document.createElement("a");
            this.href.className = "btn bg-info";
            this.href.setAttribute("href", asset + "/" + id);
            this.href.innerHTML = "Visualizza File";
            this.br = document.createElement("br");
            this.text.appendChild(this.br);
            this.text.appendChild(this.href);
        }
        this.info.appendChild(this.span1);
        this.info.appendChild(this.span2);
        this.container.appendChild(this.info);
        this.container.appendChild(this.icon);
        this.container.appendChild(this.text);
    }

    get() {
        return this.container;
    }
}

/*******************************************************
 *
 Function to update the messaage with result of ajax call
 */

function updateCardBody(path, Auth, asset) {
    $.ajax({
        url: path,
        success: function (data) {
            let cardBody = document.getElementById("messages");
            cardBody.lastChild.remove();
            let M = new messages(data, Auth, asset).get();
            cardBody.appendChild(M);
        },
	    error: function (richiesta, stato, errori) {
	        var check = document.getElementById("myModal");
	        if (check)
	            document.getElementById("myModal").remove();
	        var warning = new modalWarning();
	        this.section = document.getElementById("chatHTML5");
	        this.section.appendChild(warning.get());
	        $("#myModal").modal('show');
    }
    });
}

/********************************************************
 *
 Function to check the input
 */

function validator() {

    var message = document.getElementById("messageChat");
    var file = document.getElementById("attachment");
    var section = document.getElementById('chatHTML5');
    var check = document.getElementById("send");
    if (check)
        document.getElementById("send").remove();

    if (message.value == ""  && file.value == "") {

        let avviso1 = new avviso("Messaggio vuoto! Inserisci un file d'immagine o scrivi un messaggio");
        section.appendChild(avviso1.get());
        $("#send").modal('show');
        file.value = "";
        return false;
    }

    if (message.value.length > 200) {
        let avviso1 = new avviso("Messaggio troppo lungo");
        section.appendChild(avviso1.get());
        $("#send").modal('show');
        return false;
    }

    var filePath = file.value;
    let extension = /(\.jpg|\.jpeg|\.png|\.pdf|\.tiff|\.psd|\.bmp|\.gif)$/i;

    if (file.value.length >= 1 && !extension.exec(filePath)) {
        let avviso1 = new avviso("Estensione file non valida");
        section.appendChild(avviso1.get());
        $("#send").modal('show');
        file.value = "";
        return false;
    }

    if (filePath.length > 100) {
        let avviso1 = new avviso("File immagine troppa lunga!");
        section.appendChild(avviso1.get());
        $("#send").modal('show');
        file.value = "";
        return false;
    }

    return true;

}

/*********************************************************
 * Function to send message
 */

function send() {

    if (validator()) {

        var spinner = new loaderC();
        document.getElementById("chatHTML5").appendChild(spinner.get());
        $("#load").modal('show');
        $("#chat").submit();
    }


}
/*********************************************************
 *
 Spinner Loader
 */

class loaderC {

    constructor() {
        this.div = document.createElement("div");
        this.div.className = "modal";
        this.div.setAttribute("tabindex","-1");
        this.div.setAttribute("role","dialog");
        this.div.setAttribute("id", "load");
        this.div2 = document.createElement("div");
        this.div2.className = "modal-dialog";
        this.div3 = document.createElement("div");
        this.div3.className = "modal-content bg-info";
        this.header = new loaderHeader();
        this.div3.appendChild(this.header.get());
        this.div2.appendChild(this.div3);
        this.div.appendChild(this.div2);
    }

    get() {
        return this.div;
    }
}

class loaderHeader {

    constructor() {
        this.div = document.createElement("div");
        this.div.className = "modal-header";
        this.spinnerdiv = document.createElement("div");
        this.spinnerdiv.className = "d-flex justify-content-center";
        this.spinnerdiv2 = document.createElement("div");
        this.spinnerdiv2.className = "spinner-border";
        this.spinnerdiv2.setAttribute("role","status");
        this.spinnerspan = document.createElement("span");
        this.spinnerspan.className = "sr-only";
        this.spinnerspan.innerHTML = "Loading...";
        this.spinnerdiv2.appendChild(this.spinnerspan);
        this.spinnerdiv.appendChild(this.spinnerdiv2);
        this.div.appendChild(this.spinnerdiv);
        this.h5 = document.createElement("h5");
        this.h5.innerHTML = "&nbsp; &nbsp; &nbsp; &nbsp;  CARICAMENTO IN CORSO";
        this.div.appendChild(this.h5);
        this.button = document.createElement("button");
        this.button.className = "close";
        this.button.setAttribute("type", "button");
        this.button.setAttribute("data-dismiss", "modal");
        this.button.setAttribute("aria-label", "Close");
        this.span = document.createElement("span");
        this.span.setAttribute("aria-hidden", "true");
        this.span.innerHTML = "&times;";
        this.button.appendChild(this.span);
        this.div.appendChild(this.button);
    }

    get() {
        return this.div;
    }
}

/*********************************************************
 *
 Allert view with message
 */

class avviso {

    constructor(message) {
        this.div = document.createElement("div");
        this.div.className = "modal";
        this.div.setAttribute("tabindex","-1");
        this.div.setAttribute("role","dialog");
        this.div.setAttribute("id", "send");
        this.div2 = document.createElement("div");
        this.div2.className = "modal-dialog";
        this.div3 = document.createElement("div");
        this.div3.className = "modal-content bg-warning";
        this.header = new avvisoHeader(message);
        this.div3.appendChild(this.header.get());
        this.div2.appendChild(this.div3);
        this.div.appendChild(this.div2);
    }

    get() {
        return this.div;
    }
}

class avvisoHeader {

    constructor(message) {
        this.div = document.createElement("div");
        this.div.className = "modal-header";
        this.h4 = document.createElement("h5");
        this.h4.className = "modal-title";
        this.h4.innerHTML = message;
        this.div.appendChild(this.h4);
        this.button = document.createElement("button");
        this.button.className = "close";
        this.button.setAttribute("type", "button");
        this.button.setAttribute("data-dismiss", "modal");
        this.button.setAttribute("aria-label", "Close");
        this.span = document.createElement("span");
        this.span.setAttribute("aria-hidden", "true");
        this.span.innerHTML = "&times;";
        this.button.appendChild(this.span);
        this.div.appendChild(this.button);
    }

    get() {
        return this.div;
    }
}

/*********************************************************
 *
 Modal with error
 */

class modalWarning {

    constructor() {
        this.div = document.createElement("div");
        this.div.className = "modal";
        this.div.setAttribute("tabindex","-1");
        this.div.setAttribute("role","dialog");
        this.div.setAttribute("id", "myModal");
        this.div2 = document.createElement("div");
        this.div2.className = "modal-dialog";
        this.div3 = document.createElement("div");
        this.div3.className = "modal-content bg-danger";
        this.header = new modalHeaderWarning();
        this.body = new modalBodyWarning();
        this.div3.appendChild(this.header.get());
        this.div3.appendChild(this.body.get());
        this.div2.appendChild(this.div3);
        this.div.appendChild(this.div2);
    }

    get() {
        return this.div;
    }
}

class modalHeaderWarning {

    constructor() {
        this.div = document.createElement("div");
        this.div.className = "modal-header";
        this.h4 = document.createElement("h4");
        this.h4.className = "modal-title";
        this.h4.innerHTML = "ERRORE";
        this.div.appendChild(this.h4);
        this.button = document.createElement("button");
        this.button.className = "close";
        this.button.setAttribute("type", "button");
        this.button.setAttribute("data-dismiss", "modal");
        this.button.setAttribute("aria-label", "Close");
        this.span = document.createElement("span");
        this.span.setAttribute("aria-hidden", "true");
        this.span.innerHTML = "&times;";
        this.button.appendChild(this.span);
        this.div.appendChild(this.button);
    }

    get() {
        return this.div;
    }
}

class modalBodyWarning {

    constructor() {
        this.div = document.createElement("div");
        this.div.className = "modal-body";
        this.p = document.createElement("p");
        this.p.innerHTML = "ERRORE! Ricarica la pagina più tardi";
        this.div.appendChild(this.p);

    }

    get() {
        return this.div;
    }
}
