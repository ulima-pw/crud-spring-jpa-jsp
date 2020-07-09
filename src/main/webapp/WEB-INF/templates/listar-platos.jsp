<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
    </head>
    <body class="container">
        <h1>Platos</h1>
        <form method="GET" action="/listar_platos">
            <input type="hidden" name="modo" value="new" />
            <button class="btn btn-primary" type="submit">Agregar</button>
        </form>
        <hr/>
        <table class="table">
            <thead>
                <tr>
                    <th>Id.</th>
                    <th>Nombre</th>
                    <th>Categoría</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plato" items="${listaPlatos}">
                    <tr>
                        <td>${plato.id}</td>
                        <td>${plato.nombre}</td>
                        <td>${plato.categoria.nombre}</td>
                        <td>
                            <c:choose>
                                <c:when test="${plato.estado == 0}">
                                    Inactivo
                                </c:when>
                                <c:otherwise>
                                    Activo
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="/listar_platos?modo=edit&platoid=${plato.id}" >Editar</a>
                            <a href="/eliminar_plato?platoid=${plato.id}" >Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
        </table>
        <c:choose>
            <c:when test="${modo=='new'}">
                <div class="modal" tabindex="-1" role="dialog" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form method="POST", action="/guardar_plato">
                                <div class="modal-header">
                                    <h5 class="modal-title">Agregar Plato</h5>
                        
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            
                                <div class="modal-body">               
                                    <!-- Formulario para agregar un nuevo plato -->
                                        <div class="form-group">
                                            <label for="plato_nombre">Nombre</label>
                                            <input type="text" class="form-control" id="plato_nombre" name="plato_nombre">
                                        </div>
                                        <div class="form-group">
                                            <label for="plato_nombre">Categoria</label>
                                            <select name="plato_categoria" class="form-control">
                                                <c:forEach var="categoria" items="${listaCategorias}">
                                                    <option value="${categoria.id}" ${ categoria.id == plato.categoria.id ? "selected" : "" }>${categoria.nombre}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="plato_nombre">Categoria</label>
                                            <select name="plato_estado" class="form-control">
                                                <option value="1">Activo</option>
                                                <option value="0">Inactivo</option>
                                            </select>
                                        </div>
                                    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="modal" tabindex="-1" role="dialog" id="myModal">
                    <div class="modal-dialog">
                        <form method="POST" action="/modificar_plato">
                            <!-- Formulario de edicion-->  
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Editar Plato</h5>
                        
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                        <input type="hidden" name="plato_id" value="${plato.id}" />
                                        <div class="form-group">
                                            <label for="plato_nombre">Nombre</label>
                                            <input type="text" class="form-control" id="plato_nombre" 
                                                name="plato_nombre" value="${plato.nombre}">
                                        </div>
                                        <div class="form-group">
                                            <label for="plato_nombre">Categoria</label>
                                            <select name="plato_categoria" class="form-control">
                                                <c:forEach var="categoria" items="${listaCategorias}">
                                                    <option value="${categoria.id}">${categoria.nombre}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="plato_nombre">Categoria</label>
                                            <select name="plato_estado" class="form-control">
                                                <option value="1" ${ plato.estado == 1 ? "selected" : "" }>Activo</option>
                                                <option value="0" ${ plato.estado == 0 ? "selected" : "" }>Inactivo</option>
                                            </select>
                                        </div>
                                    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>




                <!-- Formulario para editar un plato -->
                
            </c:otherwise>
        </c:choose>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <c:if test="${abroElModal}">
            <script>
                $('#myModal').modal('toggle')
            </script>
        </c:if>
    
    </body>
    
    
</html>