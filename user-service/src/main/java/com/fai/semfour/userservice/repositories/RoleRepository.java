package com.fai.semfour.userservice.repositories;

import com.fai.semfour.userservice.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.id = :id")
    Optional<Role> findById(@Param("id") Long id);

    @Query("SELECT r FROM Role r")
    Page<Role> findAllRoles(Pageable pageable);

    @Query(
            value =
                    """
		SELECT EXISTS (
			SELECT 1
			FROM roles r
			JOIN role_permissions rp ON r.id = rp.role_id
			WHERE r.name = :name
			GROUP BY r.id
			HAVING ARRAY_AGG(rp.permission_id ORDER BY rp.permission_id) =
				ARRAY(SELECT id FROM permissions WHERE id IN :ids ORDER BY id)
		)
		""",
            nativeQuery = true)
    boolean existsByNameAndPermissions(@Param("name") String name, @Param("ids") Set<Long> ids);
}
