package com.alexeiaj.planningpokeraccount.app.adapter.output.persistence.repository

import com.alexeiaj.planningpokeraccount.app.adapter.output.persistence.entity.AccountEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository : MongoRepository<AccountEntity, String>