package com.Choppin.Choppin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Choppin.Choppin.models.Cliente;
import com.Choppin.Choppin.models.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	Iterable<Pedido> findByCliente(Cliente cliente);

	// para o método delete por idcliente
	
	Pedido findById(long id);

	// Query para a busca
//	@Query(value = "select u from Pedido u where u.cliente_id like %?1%")
//	List<Pedido> findByIdsPedidos(long id);
}
