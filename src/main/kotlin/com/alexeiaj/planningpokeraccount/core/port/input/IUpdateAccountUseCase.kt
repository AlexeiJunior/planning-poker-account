package com.alexeiaj.planningpokeraccount.core.port.input

import com.alexeiaj.planningpokeraccount.app.adapter.input.web.account.dto.AccountRequest
import com.alexeiaj.planningpokeraccount.core.domain.dto.AccountDomain

interface IUpdateAccountUseCase {
    fun update(id: Long, account: AccountRequest): AccountDomain
}