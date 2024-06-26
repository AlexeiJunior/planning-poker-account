package com.alexeiaj.planningpokeraccount.core.service

import com.alexeiaj.planningpokeraccount.app.adapter.input.web.account.dto.AccountRequest
import com.alexeiaj.planningpokeraccount.core.common.exception.AccountNotFoundException
import com.alexeiaj.planningpokeraccount.core.domain.dto.AccountDomain
import com.alexeiaj.planningpokeraccount.core.port.input.ICreateAccountUseCase
import com.alexeiaj.planningpokeraccount.core.port.input.IDeleteAccountUseCase
import com.alexeiaj.planningpokeraccount.core.port.input.IFindAllAccountUseCase
import com.alexeiaj.planningpokeraccount.core.port.input.IFindByIdAccountUseCase
import com.alexeiaj.planningpokeraccount.core.port.input.IUpdateAccountUseCase
import com.alexeiaj.planningpokeraccount.core.port.output.CreateAccountPort
import com.alexeiaj.planningpokeraccount.core.port.output.DeleteAccountPort
import com.alexeiaj.planningpokeraccount.core.port.output.FindAllAccountPort
import com.alexeiaj.planningpokeraccount.core.port.output.FindByIdAccountPort
import com.alexeiaj.planningpokeraccount.core.port.output.UpdateAccountPort
import com.alexeiaj.planningpokeraccount.core.port.output.stream.AccountCreatedProducerPort
import org.springframework.stereotype.Service

@Service
class AccountUseCaseService(
        private val createAccountPort: CreateAccountPort,
        private val updateAccountPort: UpdateAccountPort,
        private val findByIdAccountPort: FindByIdAccountPort,
        private val findAllAccountPort: FindAllAccountPort,
        private val deleteAccountPort: DeleteAccountPort,
        private val accountCreatedProducerPort: AccountCreatedProducerPort,
) : ICreateAccountUseCase, IUpdateAccountUseCase, IFindByIdAccountUseCase, IFindAllAccountUseCase, IDeleteAccountUseCase {

    override fun findAll(): List<AccountDomain> = findAllAccountPort.findAll()

    override fun findById(id: String): AccountDomain = findByIdAccountPort.findById(id)
            ?: throw AccountNotFoundException("ACCOUNT NOT FOUND")

    override fun create(account: AccountRequest): AccountDomain = createAccountPort.create(account)
            .apply { accountCreatedProducerPort.send(this) }

    override fun update(id: String, account: AccountRequest): AccountDomain = updateAccountPort.update(id, account)
            ?: throw AccountNotFoundException("ACCOUNT NOT FOUND")

    override fun delete(id: String) = deleteAccountPort.delete(id)
}