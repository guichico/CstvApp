package com.codechallenge.matches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codechallenge.designsystem.theme.CstvAppTheme
import com.codechallenge.designsystem.theme.VSColor
import com.codechallenge.model.Team

@Composable
private fun TeamColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal,
    team: Team?
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment
    ) {
        Column {
            CirclePlaceholderAsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally),
                url = team?.imgUrl
            )
            Text(
                modifier = Modifier
                    .padding(top = CstvAppTheme.spacing.medium)
                    .align(Alignment.CenterHorizontally),
                text = team?.name ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = CstvAppTheme.typography.robotoRegular2
            )
        }
    }
}

@Composable
fun CirclePlaceholderAsyncImage(modifier: Modifier, url: String?) {
    TODO("Not yet implemented")
}

@Composable
internal fun TeamVsTeamRow(
    modifier: Modifier = Modifier,
    homeTeam: Team?,
    visitingTeam: Team?
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TeamColumn(
            modifier = Modifier
                .wrapContentWidth()
                .weight(0.50f),
            horizontalAlignment = Alignment.End,
            team = homeTeam
        )
        Text(
            modifier = Modifier
                .padding(
                    horizontal = CstvAppTheme.spacing.extraLarge
                )
                .align(Alignment.CenterVertically),
            text = "vs",
            style = CstvAppTheme.typography.robotoRegular3,
            color = VSColor
        )
        TeamColumn(
            modifier = Modifier
                .wrapContentWidth()
                .weight(0.50f),
            horizontalAlignment = Alignment.Start,
            team = visitingTeam
        )
    }
}

private val teamA = Team(id = 0, name = "Team 1", imgUrl = "https://cdn.pandascore.co/images/team/image/3213/220px_team_liquidlogo_square.png")
private val teamB = Team(id = 0, name = "Team 2", imgUrl = "https://cdn.pandascore.co/images/team/image/3240/208px_mouz_2021_allmode.png")

@Preview
@Composable
private fun TeamVsTeamRowPreview() {
    CstvAppTheme {
        Column {
            TeamVsTeamRow(
                homeTeam = teamA,
                visitingTeam = teamB
            )
        }
    }
}
