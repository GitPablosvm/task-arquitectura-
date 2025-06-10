document.getElementById("registroForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const usuario = document.getElementById("usuario").value;
    const email = document.getElementById("email").value;
    const pass = document.getElementById("pass").value;

    const response = await fetch("http://localhost:8080/api/usuarios", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({nombre, apellido, usuario, email, pass})
    });

    if (response.ok) {
        alert("Usuario registrado correctamente.");
        window.location.href = "login.html";
    } else {
        alert("Error al registrar usuario.");
    }
});
