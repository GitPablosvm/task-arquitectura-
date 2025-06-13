window.addEventListener("DOMContentLoaded", async () => {
    const idUsuario = localStorage.getItem("idUsuario");
    const nombreUsuario = localStorage.getItem("nombreUsuario");

    if (!idUsuario || !nombreUsuario) {
        alert("No estás logueado.");
        window.location.href = "login.html";
        return;
    }

    // Mostrar el nombre del usuario
    const nombreSpan = document.getElementById("nombreUsuario");
    if (nombreSpan) {
        nombreSpan.textContent = nombreUsuario;
    }

    try {
        const response = await fetch(`http://localhost:8080/tarea/usuario/${idUsuario}`);
        if (!response.ok) throw new Error("Error al cargar las tareas");

        const tareas = await response.json();
        const lista = document.getElementById("listaTareas");
        lista.innerHTML = "";

        if (tareas.length === 0) {
            lista.innerHTML = "<p class='text-green-600 font-semibold'>¡Felicidades! No tienes tareas pendientes.</p>";
        } else {
            tareas.forEach(t => {
                const item = document.createElement("div");
                item.className = "flex justify-between items-center bg-white shadow-md rounded p-4 mb-2";
                item.innerHTML = `
                    <div class="flex items-center">
                        <input type="checkbox" class="mr-2" ${t.estado === "COMPLETADA" ? "checked" : ""} onchange="marcarCompleta(${t.id})">
                        <span class="${t.estado === "COMPLETADA" ? "line-through text-gray-500" : ""}">${t.descripcion}</span>
                    </div>
                    <div>
                        <button onclick="editarTarea(${t.id})" class="text-blue-500 hover:text-blue-700 mr-2">✏️</button>
                        <button onclick="eliminarTarea(${t.id})" class="text-red-500 hover:text-red-700">🗑️</button>
                    </div>
                `;
                lista.appendChild(item);
            });
        }
    } catch (error) {
        console.error(error);
        alert("Error al cargar las tareas.");
    }
});

// Marcar tarea como completada
async function marcarCompleta(id) {
    try {
        const response = await fetch(`http://localhost:8080/tarea/${id}/completar`, {
            method: 'PATCH'
        });
        if (response.ok) {
            alert("Tarea marcada como completada.");
            window.location.reload();
        } else {
            alert("Error al marcar la tarea como completada.");
        }
    } catch (error) {
        console.error("Error al marcar como completada:", error);
    }
}

// Editar tarea
async function editarTarea(id) {
    const nuevaDescripcion = prompt("Ingrese la nueva descripción de la tarea:");
    if (nuevaDescripcion === null || nuevaDescripcion.trim() === "") {
        alert("Descripción no válida.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/tarea/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ descripcion: nuevaDescripcion })
        });
        if (response.ok) {
            alert("Tarea actualizada con éxito.");
            window.location.reload();
        } else {
            alert("Error al actualizar la tarea.");
        }
    } catch (error) {
        console.error("Error al editar la tarea:", error);
    }
}

// Eliminar tarea
async function eliminarTarea(id) {
    if (!confirm("¿Estás seguro de que deseas eliminar esta tarea?")) return;

    try {
        const response = await fetch(`http://localhost:8080/tarea/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            alert("Tarea eliminada correctamente.");
            window.location.reload();
        } else {
            alert("Error al eliminar la tarea.");
        }
    } catch (error) {
        console.error("Error al eliminar la tarea:", error);
    }
}
