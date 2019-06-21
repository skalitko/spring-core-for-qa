package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.domain.SavingAccount;
import com.acme.banking.dbo.ooad.service.TransferService;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TransferServiceTest {
//    @Test
//    public void shouldUpdateAccountsStateWhenTransfer() {
//        //region Given
//        TransferService sut = new TransferService();
//        Account fromAccount = mock(Account.class);
//        Account toAccount = mock(Account.class);
//        //endregion
//
//        //region When
//        sut.transfer(fromAccount, toAccount, 100.);
//        //endregion
//
//        //region Then
//        verify(fromAccount, times(1)).withdraw(100.);
//        verify(toAccount).deposit(anyDouble());
//        //endregion
//    }

    @Test
    public void shouldUpdateAccountsStateWhenTransfer() {
        //region Given
        Account stubAcc1 = mock(Account.class);
        Account stubAcc2 = mock(Account.class);
        AccountRepository mockRepo = mock(AccountRepository.class);
        when(mockRepo.findById(0L)).thenReturn(stubAcc1);
        when(mockRepo.findById(1L)).thenReturn(stubAcc2);

        TransferService sut = new TransferService(mockRepo);
        //endregion

        //region When
        sut.transfer(0L, 1L, 100.);
        //endregion

        //region Then
        verify(mockRepo).findById(0L);
        verify(mockRepo).findById(1L);
        verify(stubAcc1, times(1)).withdraw(100.);
        verify(stubAcc2).deposit(100.);
        verify(mockRepo).save(stubAcc1);
        verify(mockRepo).save(stubAcc2);
        //endregion
    }
}