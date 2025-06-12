document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const usuario = document.getElementById("usuario").value;
    const pass = document.getElementById("pass").value;

    try {
        const response = await fetch("http://localhost:8080/api/usuarios/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ usuario, pass })
        });

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem("idUsuario", data.idUsuario);
            // Redirigir a tareas.html
            window.location.href = "tareas.html";
        } else if (response.status === 401) {
            alert("Usuario o contraseña incorrectos. Vuelva a intentarlo");
            //Borramos solo la contraseña
            document.getElementById("pass").value = "";
        } else {
            alert("Error desconocido.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Ocurrió un error. Intenta de nuevo más tarde.");
    }
});
