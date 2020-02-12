import com.desafiobackend.backend.model.Product
import com.github.mongobee.changeset.ChangeLog
import com.github.mongobee.changeset.ChangeSet
import com.mongodb.client.MongoCollection

import java.time.LocalDateTime

@ChangeLog
class ChangelogForTest {

    @ChangeSet(order = "001", id = "products collection", author = "Caue Prado")
    void productsCollection() {
        MongoCollection<Product> mycollection = db.getCollection("products")
        final Product product = Product.builder()
                .name("COMUM")
                .dateTime(LocalDateTime.now())
                .build()
        mycollection.insertOne(product)
    }
}
