package org.eclipse.slm.resource_management.model.resource

import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Location(
    @Id
    @Type(type="uuid-char")
    @Column(name = "uuid", length = 36, unique = true, nullable = false)
    var id : UUID,

    @Column(name="name", unique = true, nullable = false)
    var name: String
) {
    constructor(name: String) : this(UUID.randomUUID(), name) {
    }

    constructor(): this(UUID.randomUUID(), "")
}
