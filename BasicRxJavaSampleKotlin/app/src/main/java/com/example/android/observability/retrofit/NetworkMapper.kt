package com.example.android.observability.retrofit

import com.example.android.observability.data.blog.Blog
import com.example.android.observability.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<BlogNetWorkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetWorkEntity): Blog {
        return Blog(
                id = entity.id,
                title = entity.title,
                body = entity.body,
                image = entity.image,
                category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetWorkEntity {
        return BlogNetWorkEntity(
                id = domainModel.id,
                title = domainModel.title,
                body = domainModel.body,
                image = domainModel.image,
                category = domainModel.category
        )
    }
    fun mapFromEntityList(entities:List<BlogNetWorkEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }

}